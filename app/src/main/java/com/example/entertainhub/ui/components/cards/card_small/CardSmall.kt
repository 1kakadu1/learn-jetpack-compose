package com.example.entertainhub.ui.components.cards.card_small

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.entertainhub.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.entertainhub.ui.theme.BlackColor
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.ui.theme.customTypography
import com.example.entertainhub.utils.debugPlaceholder

@Composable
fun CardSmall(
    title: String,
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(162.dp)
            .height(245.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(216.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(216.dp)
                        .clip(RoundedCornerShape(29.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                    error = painterResource(R.drawable.ic_launcher_background)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                        .background(BlackColor, shape = RoundedCornerShape(40.dp))
                        .height(40.dp)
                        .width(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play_light_icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                title.uppercase(),
                color = MaterialTheme.colorScheme.primary,
                style = customTypography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun CardSmallPreview() {
    EntertainHubTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            CardSmall(
                title = "The Hobbit: An Unexpected Journey The Hobbit: An Unexpected Journey",
                imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg",
                onClick = {}
            )
        }
    }
}