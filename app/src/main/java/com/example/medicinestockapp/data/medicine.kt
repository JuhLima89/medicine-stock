package com.example.medicinestockapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,

    @ColumnInfo(name = "quantity")  // Mapeando "quantity" no banco de dados
    val stock: Int = 0
)

