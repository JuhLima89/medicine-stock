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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinestockapp.data.Medicine
import com.example.medicinestockapp.data.MedicineDatabase
import com.example.medicinestockapp.ui.theme.MedicineStockAppTheme
import com.example.medicinestockapp.viewmodel.MedicineViewModel
import com.example.medicinestockapp.viewmodel.MedicineViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineStockScreen() {
    var medicineName by remember { mutableStateOf("") }

    // Inicializando o contexto e o ViewModel com a ViewModelFactory
    val context = LocalContext.current
    val viewModel: MedicineViewModel = viewModel(
        factory = MedicineViewModelFactory(MedicineDatabase.getDatabase(context))
    )

    // Exibindo a lista de medicamentos gerenciados pela ViewModel
    val medicines by viewModel.allMedicines.collectAsState()

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
                viewModel.addMedicine(Medicine(name = medicineName))
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
                    onAddStock = { viewModel.addStock(medicine) },
                    onRemoveStock = { viewModel.removeStock(medicine) },
                    onDelete = { viewModel.deleteMedicine(medicine) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineItem(
    medicine: Medicine,
    onAddStock: () -> Unit,
    onRemoveStock: () -> Unit,
    onDelete: () -> Unit
) {
    var stock by remember { mutableStateOf(medicine.stock) }  // Gerencia o estado local de estoque

    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = medicine.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Stock: ${medicine.stock}")

            Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    onAddStock()  // Atualiza o estoque no ViewModel
                }) { Text("+") }

                Button(onClick = {
                    if (medicine.stock > 0) {
                        onRemoveStock()  // Atualiza o estoque no ViewModel
                    }
                }) { Text("-") }

                Button(onClick = onDelete) { Text("Delete") }
            }
        }
    }
}


