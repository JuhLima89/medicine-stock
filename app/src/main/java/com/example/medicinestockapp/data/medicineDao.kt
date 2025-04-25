package com.example.medicinestockapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    // Método para inserir um medicamento no banco
    @Insert
    suspend fun insert(medicine: Medicine)

    // Método para atualizar um medicamento existente
    @Update
    suspend fun update(medicine: Medicine)

    // Método para excluir um medicamento
    @Delete
    suspend fun delete(medicine: Medicine)

    // Método para buscar todos os medicamentos como Flow
    @Query("SELECT * FROM medicines")
    fun getAllFlow(): Flow<List<Medicine>>
}
