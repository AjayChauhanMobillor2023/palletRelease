package com.example.pallet_release_lib.repositories

import com.example.pallet_release_lib.model.GetPalletModel
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetPalletResponse

interface GetPalletRespository {

        suspend fun getPallet(getPalletResponse: GetPalletResponse): Resource<GetPalletModel?>?

}