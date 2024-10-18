package com.example.pallet_release_lib.repositories

import com.example.pallet_release_lib.ApiInterface
import com.example.pallet_release_lib.model.GetPalletModel
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetPalletResponse
import retrofit2.HttpException

class GetPalletImpl(private val newApiInterface: ApiInterface) : GetPalletRespository {
        override suspend fun getPallet(getPalletResponse: GetPalletResponse): Resource<GetPalletModel?> {
                try {
                        val myData = newApiInterface.getscanpallet("", getPalletResponse.palletCode)
                        if (myData.isSuccessful) {
                                val body = myData.body()
                                if (body != null) {
                                        return Resource.Success(body)
                                }else {
                                        return Resource.Error(myData.body()!!.msg)
                                }
                        } else {
                                val errorMessage = myData.errorBody()?.string()
                                return Resource.Error(errorMessage ?: "Failed to fetch update information")
                        }
                } catch (e: HttpException) {
                        return Resource.Error("HttpException: ${e.code()} ${e.message()}")
                }catch (e: Exception) {
                        return Resource.Error(e.message.toString() )
                }
        }
}