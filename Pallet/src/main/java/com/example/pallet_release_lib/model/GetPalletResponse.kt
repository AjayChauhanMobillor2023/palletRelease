package com.example.pallet_release_lib.model

import com.google.gson.annotations.SerializedName

data class GetPalletResponse(
        @SerializedName("palletCode")
        val palletCode: String
)
