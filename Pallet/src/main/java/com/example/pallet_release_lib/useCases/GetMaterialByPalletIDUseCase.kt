package com.example.pallet_release_lib.useCases

import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetMaterialByPalletIDResponse
import com.example.pallet_release_lib.model.GetMaterialForEmptyPalletModel
import com.example.pallet_release_lib.repositories.MappedMaterialOnPalletRepository

class GetMaterialByPalletIDUseCase(private val mappedMaterialOnPalletRepository: MappedMaterialOnPalletRepository) {
        suspend operator fun invoke(getMaterialByPalletIDResponse: GetMaterialByPalletIDResponse): Resource<GetMaterialForEmptyPalletModel?>? {
                return try {
                        mappedMaterialOnPalletRepository.getMaterial(
                                getMaterialByPalletIDResponse
                        )
                } catch (e: Exception) {
                        Resource.Error(e.message ?: "An error occurred")
                }
        }
}