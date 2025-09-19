package com.example.explorarui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.explorarui.ui.theme.ExplorarUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExplorarUITheme {
                //AppRoot()
            }
        }
    }
}

@Composable
fun AppRoot() {
    val snackbarHostState = remember { SnackbarHostState() }
    var contador by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Explorar UI") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { contador++ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription =)
            }
        },
        snackbarHost = { SnackbarHostState(snackbarHostState)}
    ){inerPadding ->
        HomeScreen(
        modifier = Modifier.padding(inerPadding),
        onShowSnakBar = {
            LaunchedEffect(contador) {
                snackbarHostState.showSnackbar("Clicks: $contador")
            }
        }
    ) }
}
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onShowSnakBar:  () -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contetAligment = Alignment.Center){
        Button(onClick = onShowSnakBar) {Text("Mostrar SnackBar")}
    }
}