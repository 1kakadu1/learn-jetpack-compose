package com.example.entertainhub.ui.components.gradient_button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entertainhub.R
import com.example.entertainhub.ui.theme.GradientGrayEndColor
import com.example.entertainhub.ui.theme.GradientGrayStartColor

data class GradientButtonProps(
    val start: Color =GradientGrayStartColor,
    val end: Color = GradientGrayEndColor
)

sealed class GradientButtonIcon {
    data class Vector(val imageVector: ImageVector) : GradientButtonIcon()
    data class PainterIcon(val painter: Painter) : GradientButtonIcon()
}

@Composable
private fun GradientIcon(
    icon: GradientButtonIcon,
    tint: Color,
    size: Int
) {
    when (icon) {
        is GradientButtonIcon.Vector -> {
            Icon(
                imageVector = icon.imageVector,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(size.dp)
            )
        }

        is GradientButtonIcon.PainterIcon -> {
            Icon(
                painter = icon.painter,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(size.dp)
            )
        }
    }
}


@Composable
fun GradientButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    radius: Dp = 58.dp,
    gradient: GradientButtonProps = GradientButtonProps(),
    iconTint: Color = Color.White,
    iconSize: Int = 28,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    label: String? = null,
    startIcon: GradientButtonIcon? = null,
    endIcon: GradientButtonIcon? = null,

) {
    Button(
        onClick = onClick,
        modifier = modifier                .background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    gradient.start,
                    gradient.end
                )
            ),
            shape = RoundedCornerShape(radius)
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = contentPadding,
        shape = RoundedCornerShape(radius)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            startIcon?.let {
                GradientIcon(
                    icon = it,
                    tint = iconTint,
                    size = iconSize
                )
            }

            if(label != null)
                Text(
                    text = label,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

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

@Preview(showBackground = true)
@Composable
fun GradientButtonPreview() {
    GradientButton(
        label = "Book",
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun GradientButtonIconPreview() {
    GradientButton(
        modifier = Modifier.size(50.dp),
        onClick = {},
        iconSize = 15,
        radius = 50.dp,
        contentPadding = PaddingValues(0.dp),
        startIcon = GradientButtonIcon.PainterIcon(
            painterResource(id = R.drawable.search_icon)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GradientButtonTextIconPreview() {Box(
    modifier = Modifier
        .width(160.dp)
){
    GradientButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        label = "Watch Trailer",
        endIcon = GradientButtonIcon.PainterIcon(painter = painterResource(R.drawable.play_light_icon))
    )
}


}