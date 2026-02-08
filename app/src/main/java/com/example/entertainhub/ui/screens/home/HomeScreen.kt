package com.example.entertainhub.ui.screens.home
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.entertainhub.ui.components.button.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entertainhub.ui.components.button.ButtonVariant
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.components.gradient_button.GradientButton
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.ui.theme.GrayColor

@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier) {
    MainScaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
        ) {
            Button(
                label = "Book Tickets",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                label = "Book",
                onClick = {},
                modifier = Modifier,
                fontSize = 16.sp,
                variant = ButtonVariant.GRAY
            )
            GradientButton(label = "Book", onClick = {})
            Box(Modifier.size(20.dp))
            Text(
                text = "Hello $name! ${MaterialTheme.colorScheme.background}",
                style = MaterialTheme.typography.headlineLarge,
                modifier = modifier,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EntertainHubTheme {
        HomeScreen("Android")
    }
}