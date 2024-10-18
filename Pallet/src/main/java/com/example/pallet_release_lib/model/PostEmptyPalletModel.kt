package com.example.pallet_release_lib.model

import com.google.gson.annotations.SerializedName

data class PostEmptyPalletModel(
        @SerializedName("msg")
        val msg: String,
        @SerializedName("status")
        val status: Boolean,
        @SerializedName("statusCode")
        val statusCode: Int
)
