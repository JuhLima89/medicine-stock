package com.example.medicinestockapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinestockapp.data.Medicine
import com.example.medicinestockapp.data.MedicineDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MedicineViewModel(private val database: MedicineDatabase) : ViewModel() {

    private val medicineDao = database.medicineDao()

    private val _allMedicines = MutableStateFlow<List<Medicine>>(emptyList())
    val allMedicines: StateFlow<List<Medicine>> = _allMedicines

    init {
        // Coleta os dados do banco de forma reativa
        viewModelScope.launch {
            medicineDao.getAllFlow().collect {
                _allMedicines.value = it
            }
        }
    }

    // Adiciona um novo medicamento ao banco
    fun addMedicine(medicine: Medicine) {
        viewModelScope.launch {
            medicineDao.insert(medicine)
            // Não precisa atualizar manualmente _allMedicines, pois o Flow já faz isso
        }
    }

    // Adiciona uma unidade ao estoque do medicamento
    fun addStock(medicine: Medicine) {
        val updatedMedicine = medicine.copy(stock = medicine.stock + 1)
        viewModelScope.launch {
            medicineDao.update(updatedMedicine)
        }
    }

    // Remove uma unidade do estoque (se houver)
    fun removeStock(medicine: Medicine) {
        if (medicine.stock > 0) {
            val updatedMedicine = medicine.copy(stock = medicine.stock - 1)
            viewModelScope.launch {
                medicineDao.update(updatedMedicine)
            }
        }
    }

    // Remove o medicamento do banco
    fun deleteMedicine(medicine: Medicine) {
        viewModelScope.launch {
            medicineDao.delete(medicine)
        }
    }
}

