package dev.rrohaill.composenavigation.main.screens.two

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
import dev.rrohaill.composenavigation.destination.ScreenTwoDestination
import dev.rrohaill.composenavigation.destination.ScreenOneResult
import dev.rrohaill.composenavigation.util.NavResult

@Composable
fun ScreenTwo(
    data: ScreenTwoDestination,
    navigateToScreenList: () -> Unit,
    goBack: () -> Unit,
    goBackWithResult: (NavResult<ScreenOneResult>) -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = data.name)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = data.description)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = navigateToScreenList) {
                Text(text = "Screen list")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                goBackWithResult(NavResult.Ok(ScreenOneResult("I am Ok result from Screen Two")))
            }) {
                Text(text = "Go back with result Ok")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                goBackWithResult(NavResult.Error(ScreenOneResult("I am Error result from Screen Two")))
            }) {
                Text(text = "Go back with result Error")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                goBackWithResult(NavResult.Cancel())
            }) {
                Text(text = "Go back with result Cancel")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = goBack) {
                Text(text = "Go back")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTwoPreview() {
    ScreenTwo(ScreenTwoDestination("I am Screen Two", "Description"), {}, {}, {})
}