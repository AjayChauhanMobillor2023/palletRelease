package com.example.pallet_release_lib.repositories

import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetMaterialByPalletIDResponse
import com.example.pallet_release_lib.model.GetMaterialForEmptyPalletModel

interface MappedMaterialOnPalletRepository {
        suspend fun getMaterial(getMaterialByPalletIDResponse: GetMaterialByPalletIDResponse) : Resource<GetMaterialForEmptyPalletModel?>?
}