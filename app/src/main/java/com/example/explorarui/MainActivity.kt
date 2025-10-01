@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.explorarui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.explorarui.ui.theme.ExplorarUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExplorarUITheme {
                AppRoot()
            }
        }
    }
}

@Composable
fun AppRoot() {
    val snackbarHostState = remember { SnackbarHostState() }
    var contador by remember { mutableStateOf(0) }
    LaunchedEffect(contador) {
        if (contador>0){
            snackbarHostState.showSnackbar("Clicks: $contador")
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Explorar UI") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { contador++ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState)}
    ){innerPadding ->
        HomeScreen(
        modifier = Modifier.padding(innerPadding),
        onShowSnackBar = {
            contador +=1
        }
    )}
}
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onShowSnackBar:  () -> Unit){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Button(onClick = onShowSnackBar) {Text("Aumentar Contador")}
    }
    ComponentsPlayground()
}

@Composable
fun ComponentsPlayground(){
    var text by remember { mutableStateOf("Hola Mundo") }
    var enabled by remember { mutableStateOf(true) }
    var sliderValue by remember { mutableStateOf(0.5f) }
    var checked by remember { mutableStateOf(false) }
    var colorToggle by remember { mutableStateOf(false) }

    val animatedTint by animateColorAsState(
        targetValue = if (colorToggle) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.primary,
        label = "tint"
    )

    Column(Modifier.verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Typographies", style = MaterialTheme.typography.headlineSmall)
        Text("BodyLarge", style = MaterialTheme.typography.bodyLarge)
        Text("HeadLineSmall", style = MaterialTheme.typography.headlineSmall)
    }
}