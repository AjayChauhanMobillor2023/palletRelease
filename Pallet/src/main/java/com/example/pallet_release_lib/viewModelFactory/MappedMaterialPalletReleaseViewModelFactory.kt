package com.example.pallet_release_lib.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pallet_release_lib.useCases.GetMaterialByPalletIDUseCase
import com.example.pallet_release_lib.viewModel.MappedMaterialPalletReleaseViewModel

class MappedMaterialPalletReleaseViewModelFactory(private val respository: GetMaterialByPalletIDUseCase): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MappedMaterialPalletReleaseViewModel(respository) as T
        }
}