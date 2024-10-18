package com.example.pallet_release_lib.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetMaterialByPalletIDResponse
import com.example.pallet_release_lib.model.GetMaterialForEmptyPalletModel
import com.example.pallet_release_lib.useCases.GetMaterialByPalletIDUseCase
import kotlinx.coroutines.launch

class MappedMaterialPalletReleaseViewModel(private val getMaterialByPalletIDUseCase: GetMaterialByPalletIDUseCase) : ViewModel(){

        private val getData = MutableLiveData<Resource<GetMaterialForEmptyPalletModel?>?>()
        val getGrnData: MutableLiveData<Resource<GetMaterialForEmptyPalletModel?>?> = getData

        fun getDataFromAPI(getMaterialByPalletIDResponse: GetMaterialByPalletIDResponse) {
                viewModelScope.launch {
                        getData.value = Resource.Loading()
                        getData.value = getMaterialByPalletIDUseCase(getMaterialByPalletIDResponse)
                }
        }
}