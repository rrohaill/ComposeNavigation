package dev.rrohaill.composenavigation.main.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rrohaill.composenavigation.destination.ListData
import dev.rrohaill.composenavigation.destination.ScreenOneResult
import dev.rrohaill.composenavigation.util.NavResult

@Composable
fun ScreenList(
    data: List<ListData>,
    navigateToOne: () -> Unit,
    goBack: () -> Unit,
    navigateToOneWithResult: (NavResult<ScreenOneResult>) -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn {
                items(data.size) { index ->
                    Text(text = data[index].name)
                    if (index < data.lastIndex) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = navigateToOne) {
                Text(text = "Screen One")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                navigateToOneWithResult(NavResult.Ok(ScreenOneResult("I am Ok result from Screen List")))
            }) {
                Text(text = "Screen One with Result Ok")
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
fun ScreenListPreview() {
    ScreenList(
        listOf(ListData("I am item 1"), ListData("I am item 2")),
        {},
        {},
        {}
    )
}