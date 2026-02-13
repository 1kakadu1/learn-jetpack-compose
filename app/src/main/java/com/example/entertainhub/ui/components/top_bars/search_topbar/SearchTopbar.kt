package com.example.entertainhub.ui.components.top_bars.search_topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.entertainhub.R
import com.example.entertainhub.ui.components.button.Button
import com.example.entertainhub.ui.components.button.ButtonIcon
import com.example.entertainhub.ui.components.button.ButtonStyles
import com.example.entertainhub.ui.components.button.ButtonVariant
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.ui.theme.TextGrayColor

@Composable
fun SearchTopBar(
    onBack: () -> Unit,
    search: String,
    onChangeSearch: (value: String) -> Unit
) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Button(
            startIcon = ButtonIcon.PainterIcon(painterResource(id = R.drawable.expand_right_light)),
            onClick = onBack,
            variant = ButtonVariant.BLACK,
            modifier = Modifier.size(50.dp),
            customStyles = ButtonStyles(
                radius = 50.dp,
                contentPadding = PaddingValues(0.dp)
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50.dp),
            value = search,
            singleLine = true,
            onValueChange = onChangeSearch,
            //label = { Text("Enter text here") },
            placeholder = { Text("Search any movies name here", style = MaterialTheme.typography.bodySmall, color = TextGrayColor) },
            textStyle= MaterialTheme.typography.bodySmall.copy(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1C1C1E),
                unfocusedContainerColor = Color(0xFF1C1C1E),
                disabledContainerColor = Color(0xFF1C1C1E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )

        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EntertainHubTheme {
        MainScaffold(
            topBar = {
                SearchTopBar(
                    onBack = {},
                    search = "",
                    onChangeSearch = { value -> {} }
                )
            },
        ) { paddingValues ->
            Box(Modifier
                .padding(paddingValues)
                .padding(20.dp)) {
                Text("Top Bar")
            }
        }
    }
}