package com.example.pallet_release_lib.useCases

import com.example.pallet_release_lib.model.GetPalletModel
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetPalletResponse
import com.example.pallet_release_lib.repositories.GetPalletRespository

class GetPalletUseCase(private val getPalletRespository: GetPalletRespository) {
        suspend operator fun invoke(getPalletResponse: GetPalletResponse): Resource<GetPalletModel?>? {
                return try {
                        getPalletRespository.getPallet(
                                getPalletResponse
                        )
                } catch (e: Exception) {
                        Resource.Error(e.message ?: "An error occurred")
                }
        }
}