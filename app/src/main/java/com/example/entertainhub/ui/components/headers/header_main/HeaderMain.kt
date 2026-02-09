package com.example.entertainhub.ui.components.headers.header_main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.entertainhub.R
import com.example.entertainhub.ui.components.gradient_button.GradientButton
import com.example.entertainhub.ui.components.gradient_button.GradientButtonIcon
import com.example.entertainhub.ui.theme.RedColor

@Composable
fun HeaderMain() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column{
            Text(
                text = "Hey!",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "Make a selection >",
                style = MaterialTheme.typography.bodyLarge.copy(color = RedColor),
                modifier = Modifier.clickable(onClick = {})
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GradientButton(
                modifier = Modifier.size(50.dp),
                onClick = {},
                iconSize = 28,
                radius = 50.dp,
                startIcon = GradientButtonIcon.PainterIcon(
                    painterResource(id = R.drawable.search_icon)
                )
            )
            GradientButton(
                modifier = Modifier.size(50.dp),
                onClick = {},
                iconSize = 28,
                radius = 50.dp,
                startIcon = GradientButtonIcon.PainterIcon(
                    painterResource(id = R.drawable.user_light)
                )
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun HeaderMainPreview() {
    HeaderMain()
}