package com.example.pallet_release_lib.repositories

import com.example.pallet_release_lib.ApiInterface
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetMaterialByPalletIDResponse
import com.example.pallet_release_lib.model.GetMaterialForEmptyPalletModel
import retrofit2.HttpException

class MappedMaterialOnPalletImpl( private val newApiInterface: ApiInterface): MappedMaterialOnPalletRepository {
        override suspend fun getMaterial(getMaterialByPalletIDResponse: GetMaterialByPalletIDResponse): Resource<GetMaterialForEmptyPalletModel?>? {
                return try {
                        val myData = newApiInterface.getMaterialByPalletPalletization("", getMaterialByPalletIDResponse.palletId)
                        if (myData.isSuccessful) {
                                val body = myData.body()
                                if (body != null) {
                                        return Resource.Success(body)
                                } else {
                                        return Resource.Error(myData.body()!!.msg)
                                }
                        } else {
                                val errorMessage = myData.errorBody()?.string()
                                return Resource.Error(
                                        errorMessage ?: "Failed to fetch update information"
                                )
                        }
                } catch (e: HttpException) {
                        Resource.Error("HttpException: ${e.code()} ${e.message()}")
                } catch (e: Exception) {
                        Resource.Error(e.message ?: "An error occurred")
                }
        }
}