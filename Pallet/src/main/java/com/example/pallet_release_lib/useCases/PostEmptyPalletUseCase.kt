package com.example.pallet_release_lib.useCases

import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.PostEmptyPalletModel
import com.example.pallet_release_lib.model.PostEmptyPalletResponse
import com.example.pallet_release_lib.repositories.PostingEmptyPalletRepository

class PostEmptyPalletUseCase (private val postingEmptyPalletRepository: PostingEmptyPalletRepository) {
        suspend operator fun invoke(postEmptyPalletResponse: PostEmptyPalletResponse): Resource<PostEmptyPalletModel?>? {
                return try {
                        postingEmptyPalletRepository.postPalletDetails(postEmptyPalletResponse)
                } catch (e: Exception) {
                        Resource.Error(e.message ?: "An error occurred")
                }
        }
}