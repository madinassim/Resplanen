package com.example.resplanen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.resplanen.ui.theme.ResplanenTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Checkbox
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


data class ResAktivitet(
    val id: Int,
    val namn: String,
    val plats: String,
    val arKlar: Boolean = false
)
class ResplanViewModel : ViewModel() {
    val aktiviteter = mutableStateListOf<ResAktivitet>()
    fun laggTill(namn: String, plats: String) { if (namn.isBlank() || plats.isBlank()) return
        val nyttId = (aktiviteter.maxOfOrNull { it.id } ?: 0) + 1

        aktiviteter.add(
        ResAktivitet(
        id = nyttId,
        namn = namn.trim(),
        plats = plats.trim()
        )
        )
        }
    fun andra(id: Int, namn: String, plats: String) {
        val index = aktiviteter.indexOfFirst { it.id == id }

        if (index != -1 && namn.isNotBlank() && plats.isNotBlank()) {
            aktiviteter[index] = aktiviteter[index].copy(
            namn = namn.trim(),
            plats = plats.trim()
            )
            }
    }

    fun markeraKlar(id: Int, klar: Boolean) {
        val index = aktiviteter.indexOfFirst { it.id == id }

        if (index != -1) {
            aktiviteter[index] = aktiviteter[index].copy(arKlar = klar)
            }
    }

    fun taBort(id: Int) {
        aktiviteter.removeAll { it.id == id }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResplanenTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("✈️ Resplanen") }
                        )
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Resplanen",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    resplanViewModel: ResplanViewModel = viewModel()
) {

    var aktivitet by remember { mutableStateOf("") }
    var plats by remember { mutableStateOf("") }
    var redigerarIndex by remember { mutableStateOf<Int?>(null) }

    val aktiviteter = resplanViewModel.aktiviteter


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val antalKlara = aktiviteter.count { it.arKlar }

        Text(
            text = "$antalKlara av ${aktiviteter.size} aktiviteter klara"
        )

        Spacer(modifier = Modifier.height(8.dp))
        if (aktiviteter.isEmpty()) {
            Text(
                text = "Din resplan är tom. Lägg till din första aktivitet!"
            )
        }

        Text(
            text = "✈️ Välkommen till $name!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = aktivitet,
            onValueChange = { aktivitet = it },
            label = { Text("Skriv en aktivitet") }
        )
        OutlinedTextField(
            value = plats,
            onValueChange = { plats = it },
            label = { Text("Ange plats") }
        )

        Button(
            onClick = {
                    if (aktivitet.isNotBlank() && plats.isNotBlank()) {
                        if (redigerarIndex == null) {
                            resplanViewModel.laggTill(aktivitet, plats)
                        } else {
                            val index = redigerarIndex

                            if (index != null && index in aktiviteter.indices) {
                                resplanViewModel.andra(
                                    id = aktiviteter[index].id,
                                    namn = aktivitet,
                                    plats = plats
                                )
                            }

                            redigerarIndex = null
                        }
                    aktivitet = ""
                    plats = ""
                }
            }
        ) {
            Text(
                if (redigerarIndex == null) "Lägg till aktivitet"
                else "Spara ändringar"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            itemsIndexed(aktiviteter) { index, item ->

            Checkbox(
                checked = item.arKlar,
                onCheckedChange = { checked ->
                    resplanViewModel.markeraKlar(item.id, checked)
                }
            )

            Text(text = "✈️ ${item.namn}")
            Text(text = "📍 ${item.plats}")

            Button(
                onClick = {
                    aktivitet = item.namn
                    plats = item.plats
                    redigerarIndex = index
                }
            ) {
                Text("Ändra")
            }

            Button(
                onClick = {
                    resplanViewModel.taBort(item.id)
                }
            ) {
                Text("Ta bort")
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
        } // Avslutar itemsIndexed
    }     // Avslutar LazyColumn

    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ResplanenTheme {
        Greeting("Android")
    }
}