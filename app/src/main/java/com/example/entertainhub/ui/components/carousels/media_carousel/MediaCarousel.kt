package com.example.entertainhub.ui.components.carousels.media_carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entertainhub.ui.components.cards.card_small.CardSmall
import com.example.entertainhub.ui.theme.EntertainHubTheme

@Composable
fun MediaCarousel(
    title: String,
    items: List<MediaCarouselItem>,
    onSeeAllClick: () -> Unit,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    showSeeAll: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            if (showSeeAll) {
                TextButton(
                    onClick = onSeeAllClick,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "See All",
                        color = MaterialTheme.colorScheme.error,  // Красный цвет
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = items,
                key = { it.id }
            ) { item ->
                CardSmall(
                    title = item.title,
                    imageUrl = item.imageUrl,
                    onClick = { onItemClick(item.id) }
                )
            }
        }
    }
}

data class MediaCarouselItem(
    val id: String,
    val title: String,
    val imageUrl: String
)


@Preview(name = "Multiple Carousels", showBackground = true)
@Composable
fun MultipleCarouselsPreview() {
    val mockItems1 = listOf(
        MediaCarouselItem(
            id = "1",
            title = "SALAAR",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "2",
            title = "FLASH",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "3",
            title = "AQUAMAN",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        )
    )

    val mockItems2 = listOf(
        MediaCarouselItem(
            id = "4",
            title = "BATMAN",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        ),
        MediaCarouselItem(
            id = "5",
            title = "JOKER",
            imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg"
        )
    )

    EntertainHubTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            MediaCarousel(
                title = "Recommended Movies",
                items = mockItems1,
                onSeeAllClick = {},
                onItemClick = {}
            )

            Spacer(modifier = Modifier.height(24.dp))

            MediaCarousel(
                title = "Trending Now",
                items = mockItems2,
                onSeeAllClick = {},
                onItemClick = {}
            )
        }
    }
}