package com.example.pallet_release_lib.model

import com.google.gson.annotations.SerializedName

data class GetMaterialForEmptyPalletModel(
        @SerializedName("data")
        val `data`: List<DataGetMaterialForEmptyPalletModel>,
        @SerializedName("location")
        val location: String,
        @SerializedName("msg")
        val msg: String,
        @SerializedName("status")
        val status: Boolean,
        @SerializedName("statusCode")
        val statusCode: Int
)

data class DataGetMaterialForEmptyPalletModel(
        @SerializedName("asnCode")
        val asnCode: String,
        @SerializedName("batchNumber")
        val batchNumber: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("grnLineNumber")
        val grnLineNumber: String,
        @SerializedName("grnNumber")
        val grnNumber: String,
        @SerializedName("itemCode")
        val itemCode: String,
        @SerializedName("itemDescription")
        val itemDescription: String,
        @SerializedName("itemId")
        val itemId: Int,
        @SerializedName("locationCode")
        val locationCode: String,
        @SerializedName("locationId")
        val locationId: Int,
        @SerializedName("qty")
        val qty: Int,
        @SerializedName("sku")
        val sku: String,
        @SerializedName("suid")
        val suid: String,
        @SerializedName("uom")
        val uom: String,
        @SerializedName("vendorCode")
        val vendorCode: String,
        @SerializedName("vendorName")
        val vendorName: String
)
