package com.example.entertainhub.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.ui.theme.GrayColor
import com.example.entertainhub.ui.theme.RedColor

enum class ButtonVariant {
    RED, GRAY
}

@Composable
fun Button(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    variant: ButtonVariant = ButtonVariant.RED,
    containerColor: Color? = null,
) {
    val baseModifier = Modifier.background(containerColor ?:  RedColor);
    val radius = if (variant === ButtonVariant.GRAY) 30.dp else 16.dp;
    val padding = if(variant === ButtonVariant.RED) PaddingValues(horizontal = 17.dp, vertical = 20.dp) else PaddingValues(horizontal = 17.dp, vertical = 10.dp);

    Button(
        onClick, modifier, shape = RoundedCornerShape(radius), colors = ButtonDefaults.buttonColors(
            containerColor = containerColor ?:  RedColor, // Set the background color
            contentColor = Color.White // Set the text/content color for contrast
        ), contentPadding = padding
    ) {
        Text(
            text = label,
            baseModifier.then(modifier),
            color = Color.White,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonRed() {
    EntertainHubTheme {
        Button(
            label = "Book Tickets",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonGray() {
    EntertainHubTheme {
        Button(
            label = "Book",
            onClick = {},
            modifier = Modifier,
            fontSize = 16.sp,
            containerColor = GrayColor,
            variant = ButtonVariant.GRAY
        )
    }
}