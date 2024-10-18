package com.example.pallet_release_lib.actitivties

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pallet_release_lib.ApiInterface
import com.example.pallet_release_lib.AppConstants
import com.example.pallet_release_lib.NewApiClient
import com.example.pallet_release_lib.R
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.actitivties.ui.theme.PalletReleaseTheme
import com.example.pallet_release_lib.actitivties.ui.theme.TextStyles
import com.example.pallet_release_lib.composeable.PalletReleaseAdapterCard
import com.example.pallet_release_lib.composeable.TopNavigationBar
import com.example.pallet_release_lib.model.GetMaterialByPalletIDResponse
import com.example.pallet_release_lib.model.GetMaterialForEmptyPalletModel
import com.example.pallet_release_lib.model.PostEmptyPalletResponse
import com.example.pallet_release_lib.repositories.MappedMaterialOnPalletImpl
import com.example.pallet_release_lib.repositories.PostingEmptyPalletImpl
import com.example.pallet_release_lib.useCases.GetMaterialByPalletIDUseCase
import com.example.pallet_release_lib.useCases.PostEmptyPalletUseCase
import com.example.pallet_release_lib.viewModel.MappedMaterialPalletReleaseViewModel
import com.example.pallet_release_lib.viewModel.PostEmptyPalletViewModel
import com.example.pallet_release_lib.viewModelFactory.MappedMaterialPalletReleaseViewModelFactory
import com.example.pallet_release_lib.viewModelFactory.PostEmptyPalletViewModelFactory
import org.json.JSONException
import org.json.JSONObject

class SecondComposeActivityPalletRelease : ComponentActivity() {

        lateinit var palletId : String
        private lateinit var viewModel : MappedMaterialPalletReleaseViewModel
        private lateinit var viewModelForPostingDetails : PostEmptyPalletViewModel
        private lateinit var dialog : Dialog
        private lateinit var confirmBtn : Button
        private lateinit var progressBar : ProgressBar

        override fun onCreate(savedInstanceState : Bundle?) {
                super.onCreate(savedInstanceState)

                palletId = intent.getStringExtra("palletID").toString()
                val newApiInterface = NewApiClient.getService()

                val repository = MappedMaterialOnPalletImpl(newApiInterface)
                val getMaterialByPalletIDUseCase = GetMaterialByPalletIDUseCase(repository)

                viewModel = ViewModelProvider(
                        this,
                        MappedMaterialPalletReleaseViewModelFactory(getMaterialByPalletIDUseCase)
                )[MappedMaterialPalletReleaseViewModel::class.java]

                val repositoryPostingDetails = PostingEmptyPalletImpl(newApiInterface)
                val postEmptyPalletUseCase = PostEmptyPalletUseCase(repositoryPostingDetails)

                viewModelForPostingDetails = ViewModelProvider(
                        this,
                        PostEmptyPalletViewModelFactory(postEmptyPalletUseCase)
                )[PostEmptyPalletViewModel::class.java]

                getPalletDetails()

                setContent {
                        PalletReleaseTheme {
                                // A surface container using the 'background' color from the theme
                                Surface(
                                        modifier = Modifier.fillMaxSize(),
                                        color = MaterialTheme.colorScheme.background
                                ) {
                                        Column(modifier = Modifier.fillMaxSize()) {

                                                TopNavigationBar(
                                                        headText = AppConstants.EMPTY_PALLET_MODULE_HEADER,
                                                        contextActivity = this@SecondComposeActivityPalletRelease
                                                )

                                                ItemListScreen(viewModel = viewModel)

                                                Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.Center
                                                ) {
                                                        Button(
                                                                onClick = {
                                                                        dialogBox()
                                                                },
                                                                content = { Text(text = "Release") },
                                                                shape = RoundedCornerShape(10.dp),
                                                                colors = ButtonDefaults.buttonColors(
                                                                        containerColor = FirstComposeActivityPalletRelease.PRIMARY_COLOR,
                                                                        contentColor = FirstComposeActivityPalletRelease.SECONDARY_COLOR
                                                                ),
                                                        )
                                                }

                                        }
                                }
                        }
                }
        }

        @Composable
        fun ItemListScreen(viewModel : MappedMaterialPalletReleaseViewModel) {
                val uiState by viewModel.getGrnData.observeAsState()

                when (uiState) {
                        is Resource.Loading -> {
                                Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                ) { CircularProgressIndicator(color = FirstComposeActivityPalletRelease.PRIMARY_COLOR) }
                        }

                        is Resource.Success -> {
                                val items =
                                        (uiState as Resource.Success<GetMaterialForEmptyPalletModel?>).data?.data
                                                ?: emptyList()

                                Column {
                                        Text(text = (uiState as Resource.Success<GetMaterialForEmptyPalletModel?>).data!!.location,
                                                modifier = Modifier.padding(start = 30.dp, top = 10.dp, bottom = 5.dp),style = TextStyles.smallMediumNormalTextStyle,
                                                color = FirstComposeActivityPalletRelease.PRIMARY_COLOR)
                                        LazyColumn(modifier = Modifier.fillMaxHeight(.91F).fillMaxWidth()) {
                                                items(items) { item ->
                                                        item.let {

                                                                PalletReleaseAdapterCard(data = item)

                                                        }

                                                }
                                        }
                                }




                        }

                        is Resource.Error -> {

                                val errorMessage = try {

                                        JSONObject(
                                                (uiState as Resource.Error<GetMaterialForEmptyPalletModel?>).message
                                                        ?: ""
                                        ).getString("msg")
                                } catch (e : JSONException) {
                                        "An error occurred"
                                }
                                Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                ) {
                                        Text(
                                                text = (errorMessage.toString())
                                        )
                                }
                        }

                        else
                        -> {
                        }
                }
        }



        private fun getPalletDetails() {
                viewModel.getDataFromAPI(GetMaterialByPalletIDResponse(palletId.toInt()))
        }

        private fun postPalletData() {
                viewModelForPostingDetails.getDataFromAPI(PostEmptyPalletResponse(palletId.toInt()))

                viewModelForPostingDetails.getGrnData.observe(this, Observer {


                        if (it!!.data != null) {


                                when (it) {

                                        is Resource.Loading -> progressBar.visible()
                                        is Resource.Success -> {
                                                progressBar.gone()
                                                finish()
                                                confirmBtn.greenColor()
                                        }

                                        is Resource.Error -> {
                                                progressBar.gone()
                                                confirmBtn.greenColor()
                                                // Handle error
                                                Toast.makeText(
                                                        applicationContext,
                                                        it.data?.msg.toString(),
                                                        Toast.LENGTH_SHORT
                                                ).show()
                                        }

                                        else -> {}
                                }
                        } else {
                                Log.e("Checking", it.data.toString())
                        }
                })
        }

        private fun dialogBox() {
                dialog = Dialog(this)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.adapter_no_of_item_confirm_pop_up)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val text : TextView = dialog.findViewById(R.id.textConfirmWithNoOfPack)
                val close : ImageButton =
                        dialog.findViewById(R.id.imgBtnCancelPickingMaterialIssuance)
                confirmBtn = dialog.findViewById(R.id.btnConfirmPickingMaterialIssuance)
                progressBar = dialog.findViewById(R.id.progressBar111)

                text.text = "Do you want to empty this pallet !!"

                close.setOnClickListener {
                        dialog.dismiss()
                        confirmBtn.isEnabled = true
                }

                confirmBtn.setOnClickListener {
                        confirmBtn.isEnabled = false
                        confirmBtn.greyColor()
                        progressBar.visibility = View.VISIBLE
                        postPalletData()
                }
                dialog.show()
        }
        fun View.visible(){
                visibility = View.VISIBLE
        }

        fun View.gone(){
                visibility = View.GONE
        }

        fun View.enable(){
                isEnabled = true
        }

        fun View.disable(){
                isEnabled = false
        }

        fun Button.greyColor(){
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.grey)
        }

        fun Button.greenColor(){
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)
        }
}

