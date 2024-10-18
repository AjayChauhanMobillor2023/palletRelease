package com.example.pallet_release_lib.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pallet_release_lib.useCases.PostEmptyPalletUseCase
import com.example.pallet_release_lib.viewModel.PostEmptyPalletViewModel

class PostEmptyPalletViewModelFactory (private val respository: PostEmptyPalletUseCase): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PostEmptyPalletViewModel(respository) as T
        }
}