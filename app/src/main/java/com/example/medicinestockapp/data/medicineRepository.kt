package com.example.medicinestockapp.data

import kotlinx.coroutines.flow.Flow

class MedicineRepository(private val dao: MedicineDao) {

    // Exposição do Flow de todos os medicamentos para a UI observar as mudanças
    val allMedicines: Flow<List<Medicine>> = dao.getAllFlow()

    // Função para inserir um novo medicamento no banco de dados
    suspend fun insert(medicine: Medicine) {
        dao.insert(medicine)
    }

    // Função para atualizar as informações de um medicamento
    suspend fun update(medicine: Medicine) {
        dao.update(medicine)
    }

    // Função para excluir um medicamento do banco de dados
    suspend fun delete(medicine: Medicine) {
        dao.delete(medicine)
    }
}
