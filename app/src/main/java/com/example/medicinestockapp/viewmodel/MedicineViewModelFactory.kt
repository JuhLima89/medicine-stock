package com.example.medicinestockapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medicinestockapp.data.MedicineDatabase

class MedicineViewModelFactory(
    private val database: MedicineDatabase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicineViewModel::class.java)) {
            return MedicineViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

