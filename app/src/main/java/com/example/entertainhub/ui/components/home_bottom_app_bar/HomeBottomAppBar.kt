package com.example.entertainhub.ui.components.home_bottom_app_bar

import com.example.entertainhub.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.entertainhub.ui.components.button.Button
import com.example.entertainhub.ui.components.gradient_button.GradientButton
import com.example.entertainhub.ui.theme.BlackColor
import androidx.compose.ui.res.painterResource
import com.example.entertainhub.ui.components.gradient_button.GradientButtonIcon

@Composable
fun HomeBottomAppBar(){
    Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(BlackColor)){
        Row(

        ){
            Button(
                onClick = {},
                label = "Movies",
            )
            GradientButton(
                onClick = {},
                startIcon = GradientButtonIcon.PainterIcon(
                    painterResource(id = R.drawable.video)
                )
            )
        }
    }
}
