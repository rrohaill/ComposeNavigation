package dev.rrohaill.composenavigation.main.screens.one

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScreenOne(
    name: String,
    navigateToScreenTwo: () -> Unit,
    navigateToScreenList: () -> Unit,
    goBack: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = name)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = navigateToScreenTwo) {
                Text(text = "Screen Two")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = navigateToScreenList) {
                Text(text = "Screen List")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = goBack) {
                Text(text = "Exit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenOnePreview() {
    ScreenOne("I am Screen One", {}, {}, {})
}