package com.example.entertainhub.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.entertainhub.R
import com.example.entertainhub.ui.theme.BlackColor

enum class ButtonVariant {
    RED, GRAY, BLACK
}
data class  ButtonStyles(
    val containerColor: Color? = null,
    val contentColor: Color? = null,
    val radius: Dp? = null,
    val contentPadding: PaddingValues? = null )


sealed class ButtonIcon {
    data class Vector(val imageVector: ImageVector) : ButtonIcon()
    data class PainterIcon(val painter: Painter) : ButtonIcon()
}

@Composable
private fun GradientIcon(
    icon: ButtonIcon,
    tint: Color,
    size: Int
) {
    when (icon) {
        is ButtonIcon.Vector -> {
            Icon(
                imageVector = icon.imageVector,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(size.dp)
            )
        }

        is ButtonIcon.PainterIcon -> {
            Icon(
                painter = icon.painter,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(size.dp)
            )
        }
    }
}

private fun getDefaultStyles(variant: ButtonVariant? = null): ButtonStyles{
     return when (variant) {
        ButtonVariant.RED -> ButtonStyles(
            containerColor = RedColor,
            contentColor = Color.White,
            radius = 16.dp,
            contentPadding = PaddingValues(horizontal = 17.dp, vertical = 20.dp)
        )
        ButtonVariant.GRAY -> ButtonStyles(
            containerColor = GrayColor,
            contentColor = Color.White,
            radius = 30.dp,
            contentPadding = PaddingValues(horizontal = 17.dp, vertical = 10.dp)
        )
        ButtonVariant.BLACK -> ButtonStyles(
            containerColor = BlackColor,
            contentColor = Color.White,
            radius = 32.dp,
            contentPadding = PaddingValues(10.dp)
        )
        else -> ButtonStyles(
            containerColor = RedColor,
            contentColor = Color.White,
            radius = 16.dp,
            contentPadding = PaddingValues(horizontal = 17.dp, vertical = 20.dp)
        )
    }
}

private fun mergeStyles(
    default: ButtonStyles,
    custom: ButtonStyles?
): ButtonStyles {
    if (custom == null) return default

    return ButtonStyles(
        containerColor = custom.containerColor ?: default.containerColor ?: RedColor,
        contentColor = custom.contentColor ?: default.contentColor ?: Color.White,
        radius = custom.radius ?: default.radius ?: 16.dp,
        contentPadding = custom.contentPadding ?: default.contentPadding ?: PaddingValues(16.dp)
    )
}

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    iconTint: Color = Color.White,
    iconSize: Int = 28,
    label: String? = null,
    variant: ButtonVariant? = null,
    customStyles: ButtonStyles? = null,
    startIcon: ButtonIcon? = null,
    endIcon: ButtonIcon? = null,
) {

    val styles = mergeStyles(
        default = getDefaultStyles(variant),
        custom = customStyles
    )
    MaterialButton(
        onClick, modifier, shape = RoundedCornerShape(styles.radius!!), colors = ButtonDefaults.buttonColors(
            containerColor = styles.containerColor!!,
            contentColor = styles.contentColor!!
        ), contentPadding = styles.contentPadding!!
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            startIcon?.let {
                GradientIcon(
                    icon = it,
                    tint = iconTint,
                    size = iconSize
                )
            }
            if(label != null){
                Text(
                    text = label,
                    color = Color.White,
                    fontSize = fontSize,
                    textAlign = TextAlign.Center,
                )
            }

            endIcon?.let {
                GradientIcon(
                    icon = it,
                    tint = iconTint,
                    size = iconSize
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
            startIcon = ButtonIcon.Vector(Icons.Filled.Info),
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonIconRight() {
    EntertainHubTheme {
        Button(
            label = "Book Tickets",
            onClick = {},
            endIcon = ButtonIcon.Vector(Icons.Filled.Info))
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonIconLeftRight() {
    EntertainHubTheme {
        Button(
            label = "Book Tickets",
            onClick = {},
            startIcon = ButtonIcon.Vector(Icons.Filled.Info), endIcon = ButtonIcon.Vector(Icons.Filled.Info),)
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ButtonBlack() {
    EntertainHubTheme {
        Button(
            onClick = {},
            startIcon = ButtonIcon.Vector(Icons.Filled.Info),
            variant = ButtonVariant.BLACK)

    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonRes() {
    EntertainHubTheme {
        Button(
            onClick = {},
            startIcon = ButtonIcon.PainterIcon(painterResource(R.drawable.video)),
            label = "Video"
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun ButtonResCustom() {
    EntertainHubTheme {
        Button(
            onClick = {},
            startIcon = ButtonIcon.PainterIcon(painterResource(R.drawable.video)),
            iconSize = 18,
            label = "Video",
            customStyles = ButtonStyles(contentPadding = PaddingValues(4.dp), radius = 4.dp)
        )
    }
}