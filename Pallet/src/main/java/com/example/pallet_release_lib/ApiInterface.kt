package com.example.pallet_release_lib

import com.example.pallet_release_lib.model.GetMaterialForEmptyPalletModel
import com.example.pallet_release_lib.model.GetPalletModel
import com.example.pallet_release_lib.model.PostEmptyPalletModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {


        @GET("info/validate_pallet")
         suspend fun getscanpallet(
                @Header("Authorization") token: String,
                @Query("pallet_code") pallet_code: String,
        ): Response<GetPalletModel>

        @GET("palletization/get_material_mapped_to_pallet")
         suspend fun getMaterialByPalletPalletization(
                @Header("Authorization") token: String,
                @Query("palletId") palletId: Int
        ): Response<GetMaterialForEmptyPalletModel>

        @POST("palletization/release_pallet")
        suspend fun postEmptyPallet(
                @Header("Authorization") token: String,
                @Query("palletId") palletId: Int
        ): Response<PostEmptyPalletModel>

}