package com.example.pallet_release_lib.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pallet_release_lib.model.GetPalletModel
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.GetPalletResponse
import com.example.pallet_release_lib.useCases.GetPalletUseCase
import kotlinx.coroutines.launch

class GetPalletReleasePalletViewModel (private val getPalletUseCase: GetPalletUseCase) : ViewModel(){

        private val getData = MutableLiveData<Resource<GetPalletModel?>?>()
        val getGrnData: MutableLiveData<Resource<GetPalletModel?>?> = getData
        fun getDataFromAPI(getPalletResponse: GetPalletResponse) {
                viewModelScope.launch {
                        getData.value = Resource.Loading()
                        getData.value = getPalletUseCase(getPalletResponse)
                }
        }
}