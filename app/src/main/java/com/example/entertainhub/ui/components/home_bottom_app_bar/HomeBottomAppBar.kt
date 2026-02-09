package com.example.entertainhub.ui.components.home_bottom_app_bar

import com.example.entertainhub.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.entertainhub.ui.components.button.Button
import com.example.entertainhub.ui.components.gradient_button.GradientButton
import com.example.entertainhub.ui.theme.BlackColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.entertainhub.ui.components.button.ButtonIcon
import com.example.entertainhub.ui.components.button.ButtonStyles
import com.example.entertainhub.ui.components.gradient_button.GradientButtonIcon
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.theme.EntertainHubTheme

@Composable
fun HomeBottomAppBar() {
    Box(
        Modifier
            .offset(y = (-20).dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(BlackColor, shape = RoundedCornerShape(38.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.height(50.dp),
                onClick = {},
                label = "Movies",
                startIcon = ButtonIcon.PainterIcon(
                    painterResource(id = R.drawable.video)
                ),
                customStyles = ButtonStyles(
                    radius = 41.dp,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                )
            )
            GradientButton(
                modifier = Modifier.size(50.dp),
                onClick = {},
                iconSize = 28,
                startIcon = GradientButtonIcon.PainterIcon(
                    painterResource(id = R.drawable.play_icon)
                )
            )
            GradientButton(
                modifier = Modifier.size(50.dp),
                onClick = {},
                iconSize = 28,
                startIcon = GradientButtonIcon.PainterIcon(
                    painterResource(id = R.drawable.ticket_icon)
                )
            )
            GradientButton(
                modifier = Modifier.size(50.dp),
                onClick = {},
                iconSize = 28,
                startIcon = GradientButtonIcon.PainterIcon(
                    painterResource(id = R.drawable.more_vert_icon)
                )
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    EntertainHubTheme {
        MainScaffold(
            bottomBar = { HomeBottomAppBar() },
        ) {
            Text("Home bottom appbar")
        }
    }
}