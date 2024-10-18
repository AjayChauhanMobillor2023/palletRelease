package com.example.pallet_release_lib.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pallet_release_lib.Resource
import com.example.pallet_release_lib.model.PostEmptyPalletModel
import com.example.pallet_release_lib.model.PostEmptyPalletResponse
import com.example.pallet_release_lib.useCases.PostEmptyPalletUseCase
import kotlinx.coroutines.launch

class PostEmptyPalletViewModel (private val postEmptyPalletUseCase: PostEmptyPalletUseCase) : ViewModel(){

        private val getData = MutableLiveData<Resource<PostEmptyPalletModel?>?>()
        val getGrnData: MutableLiveData<Resource<PostEmptyPalletModel?>?> = getData
        fun getDataFromAPI(postEmptyPalletResponse: PostEmptyPalletResponse) {
                viewModelScope.launch {
                        getData.value = Resource.Loading()
                        getData.value = postEmptyPalletUseCase(postEmptyPalletResponse)
                }
        }
}