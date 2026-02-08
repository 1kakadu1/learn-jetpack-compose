package com.example.entertainhub.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button as MaterialButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.ui.theme.GrayColor
import com.example.entertainhub.ui.theme.RedColor
import androidx.compose.material.icons.filled.Info

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
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    iconTint: Color = Color.White,
    iconSize: Int = 28
) {
    //TODO: Подумать как иначе сделать и дефолт убрать, если variant == null
    val (containerColor, radius, padding) = when (variant) {
        ButtonVariant.RED -> Triple(
            RedColor,
            16.dp,
            PaddingValues(horizontal = 17.dp, vertical = 20.dp)
        )
        ButtonVariant.GRAY -> Triple(
            GrayColor,
            30.dp,
            PaddingValues(horizontal = 17.dp, vertical = 10.dp)
        )
    }
    MaterialButton(
        onClick, modifier, shape = RoundedCornerShape(radius), colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, // Set the background color
            contentColor = Color.White // Set the text/content color for contrast
        ), contentPadding = padding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (startIcon != null) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier
                        .size(iconSize.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = label,
                Modifier.then(modifier),
                color = Color.White,
                fontSize = fontSize,
                textAlign = TextAlign.Center,
            )
            if (endIcon != null) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = null,
                    tint = iconTint ,
                    modifier = Modifier
                        .size(iconSize.dp)
                        .padding(start = 8.dp)
                )
            }
        }
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
            variant = ButtonVariant.GRAY
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonIconLeft() {
    EntertainHubTheme {
        Button(
            label = "Book Tickets",
            onClick = {},
            startIcon = Icons.Filled.Info)
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonIconRight() {
    EntertainHubTheme {
        Button(
            label = "Book Tickets",
            onClick = {},
            endIcon = Icons.Filled.Info)
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonIconLeftRight() {
    EntertainHubTheme {
        Button(
            label = "Book Tickets",
            onClick = {},
            startIcon = Icons.Filled.Info,
            endIcon = Icons.Filled.Info)
    }
}