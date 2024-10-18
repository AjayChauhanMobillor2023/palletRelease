package com.example.pallet_release_lib.model

import com.google.gson.annotations.SerializedName

data class PostEmptyPalletResponse(
        @SerializedName("palletId")
        val palletId : Int
)
