package com.example.pallet_release_lib.actitivties

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pallet_release_lib.AppConstants
import com.example.pallet_release_lib.NewApiClient
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.actitivties.ui.theme.PalletReleaseTheme
import com.example.pallet_release_lib.composeable.ScanHintCard
import com.example.pallet_release_lib.composeable.StatusAndScanButton
import com.example.pallet_release_lib.composeable.TopNavigationBar
import com.example.pallet_release_lib.model.GetPalletResponse
import com.example.pallet_release_lib.repositories.GetPalletImpl
import com.example.pallet_release_lib.useCases.GetPalletUseCase
import com.example.pallet_release_lib.viewModel.GetPalletReleasePalletViewModel
import com.example.pallet_release_lib.viewModelFactory.GetPalletReleasePalletViewModelFactory
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import org.json.JSONException
import org.json.JSONObject

class FirstComposeActivityPalletRelease : ComponentActivity() {

        private var scanned_info = ""
        private lateinit var viewModel : GetPalletReleasePalletViewModel

        private lateinit var baseURL :String
        private lateinit var primaryColor :String
        private lateinit var secondaryColor :String
        private lateinit var tertiaryColor :String

        override fun onCreate(savedInstanceState : Bundle?) {
                super.onCreate(savedInstanceState)

                baseURL = intent.getStringExtra("baseURL") ?:""
                primaryColor = intent.getStringExtra("primaryColor") ?:""
                secondaryColor = intent.getStringExtra("secondaryColor") ?:""
               tertiaryColor= intent.getStringExtra("tertiaryColor") ?:""

                BASE_URL = baseURL
                PRIMARY_COLOR = Color(0xFF0F1731)
                SECONDARY_COLOR = Color(0xFFA6A6A6)
                TERTIARY_COLOR = Color(0xFF0F1731)

                val newApiInterface = NewApiClient.getService()

                val repository = GetPalletImpl(newApiInterface)
                val getPalletUseCase = GetPalletUseCase(repository)
                viewModel = ViewModelProvider(
                        this,
                        GetPalletReleasePalletViewModelFactory(getPalletUseCase)
                )[GetPalletReleasePalletViewModel::class.java]

                viewModel.getGrnData.observe(this, Observer {
//            Log.e("CHECK",it?.message.toString())


                        when (it) {
                                is Resource.Loading -> {
//                                        binding.progressBarEmptyPallet.visible()
                                }
                                is Resource.Success -> {


                                        var palletIDD = ""
                                        var pallet = ""
                                        for (i in it.data!!.data) {
                                                palletIDD = i.palletId.toString()
                                                pallet = i.palletCode.toString()
                                        }


//                                        binding.progressBarEmptyPallet.gone()

                                        val intent =
                                                Intent(this, SecondComposeActivityPalletRelease::class.java)
                                        intent.putExtra("palletID", palletIDD)
                                        intent.putExtra("pallettt", pallet)
                                        startActivity(intent)

                                }

                                is Resource.Error -> {
                                        val errorMessage = try {

                                                JSONObject(it.message ?: "").getString("msg")
                                        } catch (e : JSONException) {
                                                "An error occurred"
                                        }
                                        Log.e("testing", "hhkj ${it.message}")
//                                        binding.progressBarEmptyPallet.gone()
                                        Toast.makeText(
                                                this@FirstComposeActivityPalletRelease,
                                                errorMessage,
                                                Toast.LENGTH_LONG
                                        ).show()
                                }

                                else -> {
                                        Log.e("testing", "hhk12342134j")
                                }
                        }

                })

                setContent {
                        PalletReleaseTheme {
                                // A surface container using the 'background' color from the theme
                                Surface(
                                        modifier = Modifier.fillMaxSize(),
                                        color = MaterialTheme.colorScheme.background
                                ) {

                                        var currentStatus by remember { mutableStateOf("QR Code") }


                                        Column {
                                                TopNavigationBar(
                                                        headText = AppConstants.EMPTY_PALLET_MODULE_HEADER,
                                                        contextActivity = this@FirstComposeActivityPalletRelease
                                                )

                                                ScanHintCard(hint = "Scan your Pallet!")
                                                Spacer(modifier = Modifier.weight(1f))
                                                Box(
                                                        modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(
                                                                        bottom = 56.dp,
                                                                        end = 16.dp
                                                                ),
                                                        contentAlignment = Alignment.BottomEnd
                                                ) {


                                                        // Use combined composable function here
                                                        StatusAndScanButton(
                                                                currentStatus = currentStatus,
                                                                onStatusChange = { status ->
                                                                        currentStatus = status
                                                                        Log.e("checkingstatus2", currentStatus)
                                                                },
                                                                onScanButtonClick = { status ->
                                                                        Log.e("checkingstatus", status)
                                                                        handleScanButtonClick(status) // Pass the current status to the function
                                                                }, deviceType = "Samsung", context = this@FirstComposeActivityPalletRelease // Pass the device type here
                                                        )


                                                }
                                        }

                                }
                        }
                }
        }

        private fun QRCodeInputFromMobileCamera() {

                if (ContextCompat.checkSelfPermission(
                                this@FirstComposeActivityPalletRelease,
                                android.Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                ) {
                        showCamera()
                } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                        Toast.makeText(
                                this@FirstComposeActivityPalletRelease,
                                "Camera permission required",
                                Toast.LENGTH_LONG
                        ).show()
                } else {
                        requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
        }
        private fun showCamera() {
                val options = ScanOptions()
                options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
                options.setPrompt("Scan QR code")
                options.setCameraId(0)
                options.setBeepEnabled(true)
                options.setBarcodeImageEnabled(true)
                options.setOrientationLocked(true)

                scanLauncher.launch(options)
        }
        private val scanLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
                run {
                        if (result.contents != null) {
                                scanned_info = result.contents.toString()
                                Log.d("fella" ,"hit")
                                getPallet(scanned_info)


                        } else {
                                Toast.makeText(this@FirstComposeActivityPalletRelease, "Cancelled", Toast.LENGTH_LONG).show()
                        }
                }
        }
        private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                        showCamera()
                }
                else {
                        ActivityCompat.requestPermissions(
                                this@FirstComposeActivityPalletRelease, arrayOf(
                                        android.Manifest.permission.CAMERA,
                                ), 101
                        )
                }
        }

        private fun getPallet(scanned_info: String) {



                viewModel.getDataFromAPI(GetPalletResponse(scanned_info))

        }

        private fun handleScanButtonClick(currentStatus: String) {

//                val deviceType = DeviceUtils.getDeviceType()
//
////         val deviceType = "CipherLab"
//
//
//                if (deviceType.contains(AppConstants.industrialDevice)) {
//                        if (currentStatus == "RFID") {
//                                status=currentStatus
//                                Log.e("checkingrfie",currentStatus)
//                                val filter = IntentFilter().apply {
//                                        addAction(com.cipherlab.rfid.GeneralString.Intent_RFIDSERVICE_CONNECTED)
//                                        addAction(com.cipherlab.rfid.GeneralString.Intent_RFIDSERVICE_TAG_DATA)
//                                }
//                                registerReceiver(myRFIDDataReceiver, filter)
//
//                                val re = mRfidManager.SetScanMode(ScanMode.Single)
//                                if (re != com.cipherlab.rfid.ClResult.S_OK.ordinal) {
//                                        val m = mRfidManager.GetLastError()
//                                        Log.e(TAG, "GetLastError = $m")
//                                } else {
//                                        Log.e(TAG, "SetScanMode Continuous success")
//                                        ForSetTriggerSwitchModeTest()
//                                }
//                        } else if (currentStatus == "QR Code") {
//
//                                status=currentStatus
//
//                                Log.e("checkingqrcodestat",currentStatus)
//
//                                val filter = IntentFilter().apply {
//                                        addAction(GeneralString.Intent_SOFTTRIGGER_DATA)
//                                        addAction(GeneralString.Intent_PASS_TO_APP)
//                                        addAction(GeneralString.Intent_READERSERVICE_CONNECTED)
//                                }
//                                registerReceiver(myDataReceiver, filter)
//
//                                ExeSampleCode()
//                        }
//                } else {
//
//                        status=currentStatus

//                        Log.e("checkingqmob",currentStatus)

                        QRCodeInputFromMobileCamera()
//                }
        }

        companion object{
                var BASE_URL = ""
                var PRIMARY_COLOR = Color(0xFF000000)
                var SECONDARY_COLOR = Color(0xFF000000)
                var TERTIARY_COLOR = Color(0xFF000000)
        }



}

