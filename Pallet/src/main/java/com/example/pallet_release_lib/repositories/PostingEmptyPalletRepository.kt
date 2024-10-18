package com.example.pallet_release_lib.repositories

import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.PostEmptyPalletModel
import com.example.pallet_release_lib.model.PostEmptyPalletResponse

interface PostingEmptyPalletRepository {
        suspend fun postPalletDetails(postEmptyPalletResponse: PostEmptyPalletResponse): Resource<PostEmptyPalletModel?>?

}