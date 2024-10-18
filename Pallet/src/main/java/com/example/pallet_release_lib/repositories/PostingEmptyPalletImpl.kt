package com.example.pallet_release_lib.repositories

import com.example.pallet_release_lib.ApiInterface
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.PostEmptyPalletModel
import com.example.pallet_release_lib.model.PostEmptyPalletResponse
import retrofit2.HttpException

class PostingEmptyPalletImpl (private val newApiInterface: ApiInterface) : PostingEmptyPalletRepository{
        override suspend fun postPalletDetails(postEmptyPalletResponse: PostEmptyPalletResponse): Resource<PostEmptyPalletModel?>? {
                try {
                        val myData = newApiInterface.postEmptyPallet("",postEmptyPalletResponse.palletId)
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