package com.example.medicinestockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicinestockapp.ui.theme.MedicineStockAppTheme

// Data class for medicine
data class Medicine(val name: String, val stock: MutableState<Int> = mutableStateOf(0))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicineStockAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MedicineStockScreen()
                }
            }
        }
    }
}

@Composable
fun MedicineStockScreen() {
    var medicineName by remember { mutableStateOf("") }
    val medicines = remember { mutableStateListOf<Medicine>() }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text("Medicine name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (medicineName.isNotBlank()) {
                medicines.add(Medicine(medicineName))
                medicineName = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Add Medicine")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(medicines) { medicine ->
                MedicineItem(
                    medicine = medicine,
                    onAddStock = { medicine.stock.value++ },
                    onRemoveStock = { if (medicine.stock.value > 0) medicine.stock.value-- },
                    onDelete = { medicines.remove(medicine) }
                )
            }
        }
    }
}

@Composable
fun MedicineItem(
    medicine: Medicine,
    onAddStock: () -> Unit,
    onRemoveStock: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = medicine.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Stock: ${medicine.stock.value}")

            Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onAddStock) { Text("+") }
                Button(onClick = onRemoveStock) { Text("-") }
                Button(onClick = onDelete) { Text("Delete") }
            }
        }
    }
}