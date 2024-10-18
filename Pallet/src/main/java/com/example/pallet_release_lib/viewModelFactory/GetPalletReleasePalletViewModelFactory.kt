package com.example.pallet_release_lib.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pallet_release_lib.useCases.GetPalletUseCase
import com.example.pallet_release_lib.viewModel.GetPalletReleasePalletViewModel

class GetPalletReleasePalletViewModelFactory (private val respository : GetPalletUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GetPalletReleasePalletViewModel(respository) as T
        }
}