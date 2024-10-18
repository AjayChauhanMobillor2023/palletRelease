package com.example.pallet_release_lib.actitivties

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pallet_release_lib.ApiInterface
import com.example.pallet_release_lib.model.GetPalletModel
import com.example.pallet_release_lib.databinding.ActivityMainLibBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FirstActivityPalletReleaseLibrary : AppCompatActivity() {

        private lateinit var binding : ActivityMainLibBinding
        private var palletID = ""
        private var baseURL : String =""

        private val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted : Boolean ->
                        if (isGranted) {
                                showCamera()
                        } else {
                                ActivityCompat.requestPermissions(
                                        this@FirstActivityPalletReleaseLibrary, arrayOf(
                                                android.Manifest.permission.CAMERA,
                                        ), 101
                                )
                        }
                }

        private val scanLauncher =
                registerForActivityResult(ScanContract()) { result : ScanIntentResult ->
                        run {
                                if (result.contents != null) {
                                        palletID = result.contents.toString()
//                                        getPallet()
                                } else {
                                        Toast.makeText(
                                                this@FirstActivityPalletReleaseLibrary,
                                                "Cancelled",
                                                Toast.LENGTH_LONG
                                        ).show()
                                }
                        }
                }

        override fun onCreate(savedInstanceState : Bundle?) {
                super.onCreate(savedInstanceState)
                
                binding = ActivityMainLibBinding.inflate(layoutInflater)
                setContentView(binding.root)

                baseURL = intent.getStringExtra("baseURL")?:""

                BASE_URL = baseURL

                Log.e("checkBaseURL",baseURL.toString())

                binding.btnScan.setOnClickListener {
                        val intent = Intent(this@FirstActivityPalletReleaseLibrary , FirstComposeActivityPalletRelease::class.java)
//                        intent.putExtra("id",palletId.toString())
//                        intent.putExtra("baseURL",baseURL)
                        startActivity(intent)
//                        QRCodeInputFromMobileCamera()
                }
                binding.btnback.setOnClickListener {
                        finish()
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

        private fun QRCodeInputFromMobileCamera() {

                if (ContextCompat.checkSelfPermission(
                                this@FirstActivityPalletReleaseLibrary,
                                android.Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                ) {
                        showCamera()
                } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                        Toast.makeText(
                                this@FirstActivityPalletReleaseLibrary,
                                "Camera permission required",
                                Toast.LENGTH_LONG
                        ).show()
                } else {
                        requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
        }

        private fun getService(context: Context?): ApiInterface {



//        val gson = GsonBuilder()
//            .setLenient()
//            .create()

                // Initialize ApiService if not initialized yet
                val retrofit = Retrofit.Builder().baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okhttpClient(context!!)) // Add our Okhttp client
                        .build()
                return retrofit.create(ApiInterface::class.java)
        }

        private fun okhttpClient(context: Context): OkHttpClient {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                return OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.MINUTES)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build()
        }

//        private fun getPallet(){
//
//                getService(this@FirstActivityPalletReleaseLibrary).getscanpallet("",palletID).enqueue(object : Callback<GetPalletModel?> {
//                        override fun onResponse(
//                                call : Call<GetPalletModel?>,
//                                response : Response<GetPalletModel?>
//                        ) {
//                                if (response.isSuccessful){
//
//                                        val palletId = response.body()?.data?.first()?.palletId ?: ""
//                                        Log.e("check",palletId.toString())
//                                        val intent = Intent(this@FirstActivityPalletReleaseLibrary , FirstComposeActivityPalletRelease::class.java)
//                                        intent.putExtra("id",palletId.toString())
//                                        intent.putExtra("baseURL",baseURL)
//                                        startActivity(intent)
//
//                                }else{
//                                        Toast.makeText(this@FirstActivityPalletReleaseLibrary, "API call fail", Toast.LENGTH_SHORT).show()
//                                }
//                        }
//
//                        override fun onFailure(call : Call<GetPalletModel?>, t : Throwable) {
//                                Toast.makeText(this@FirstActivityPalletReleaseLibrary, t.localizedMessage, Toast.LENGTH_SHORT).show()
//                        }
//                })
//        }

        companion object{
                var BASE_URL = ""

        }
}


