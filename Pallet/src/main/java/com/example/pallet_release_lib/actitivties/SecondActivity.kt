package com.example.pallet_release_lib.actitivties

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pallet_release_lib.ApiInterface
import com.example.pallet_release_lib.PalletReleaseAdapter
import com.example.pallet_release_lib.databinding.ActivitySecondBinding
import com.example.pallet_release_lib.model.DataGetMaterialForEmptyPalletModel
import com.example.pallet_release_lib.model.GetMaterialForEmptyPalletModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SecondActivity : AppCompatActivity() {

        lateinit var binding : ActivitySecondBinding
        private lateinit var baseURL : String

         private var dataGetMaterialByPalletIDModel = ArrayList<DataGetMaterialForEmptyPalletModel>()


        override fun onCreate(savedInstanceState : Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivitySecondBinding.inflate(layoutInflater)
                setContentView(binding.root)

                val palletId = intent.getStringExtra("id").toString()
                baseURL = intent.getStringExtra("baseURL").toString()

                Log.e("checkBaseURL10",baseURL.toString())
                Log.e("checkBaseURL1",palletId.toString())

//                getMaterial(palletId)

                binding.btnback.setOnClickListener {
                        finish()
                }


        }



//        private fun getMaterial(palletId : String) {
//
//                getService(this@SecondActivity).getMaterialByPalletPalletization("", palletId = palletId.toInt()).enqueue(object : Callback<GetMaterialForEmptyPalletModel?> {
//                        override fun onResponse(
//                                call : Call<GetMaterialForEmptyPalletModel?>,
//                                response : Response<GetMaterialForEmptyPalletModel?>
//                        ) {
//                                if (response.isSuccessful){
//
//                                        for (myData in response.body()!!.data){
//                                                dataGetMaterialByPalletIDModel.add(myData)
//                                        }
//
//                                        val adapter =
//                                                PalletReleaseAdapter(
//                                                        this@SecondActivity,
//                                                        dataGetMaterialByPalletIDModel
//                                                )
//                                        binding.recyclerViewSecondPalletRelease.adapter =
//                                                adapter
//                                        binding.recyclerViewSecondPalletRelease.layoutManager =
//                                                LinearLayoutManager(this@SecondActivity)
//                                }else{
//                                        Toast.makeText(this@SecondActivity, "something went wrong", Toast.LENGTH_SHORT)
//                                                .show()
//                                }
//                        }
//
//                        override fun onFailure(
//                                call : Call<GetMaterialForEmptyPalletModel?>,
//                                t : Throwable
//                        ) {
//                                Toast.makeText(this@SecondActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()                        }
//                })
//
//
//
//
//
//        }

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
}