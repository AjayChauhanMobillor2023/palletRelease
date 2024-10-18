package com.example.pallet_release_lib.model

import com.google.gson.annotations.SerializedName

data class GetPalletModel(
        @SerializedName("data")
        val `data`: List<DataGetPalletModel>,
        @SerializedName("msg")
        val msg: String,
        @SerializedName("status")
        val status: Boolean,
        @SerializedName("statusCode")
        val statusCode: Int
)

data class DataGetPalletModel(
        @SerializedName("palletId")
        val palletId: Int,
        @SerializedName("isEmpty")
        val isEmpty: Int,
        @SerializedName("palletCode")
        val palletCode: String,
        @SerializedName("palletName")
        val palletName: String,
        @SerializedName("palletTypeId")
        val palletTypeId: Int,
        @SerializedName("isActive")
        val isActive: Int,
        @SerializedName("palletType")
        val palletType: String
)
