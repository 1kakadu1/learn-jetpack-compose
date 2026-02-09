package com.example.entertainhub.ui.components.cards.card_movie

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.entertainhub.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.entertainhub.ui.components.button.Button
import com.example.entertainhub.ui.components.button.ButtonIcon
import com.example.entertainhub.ui.components.button.ButtonStyles
import com.example.entertainhub.ui.components.button.ButtonVariant
import com.example.entertainhub.ui.components.gradient_button.GradientButton
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.ui.theme.GrayColor
import com.example.entertainhub.ui.theme.RedColor

@Composable
fun MovieCard(
    title: String,
    imageUrl: String,
    ageRating: String,
    language: String,
    genre: String,
    format: String,
    onWatchTrailerClick: () -> Unit,
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier,
    trendingLabel: String = "TRENDING"
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(325.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(279.dp)
                .background(GrayColor, shape = RoundedCornerShape(48.dp))
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(48.dp)),
                contentScale = ContentScale.Crop,
                placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .absoluteOffset(y = 46.dp)

            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = onWatchTrailerClick,
                            label = "Watch Trailer",
                            endIcon = ButtonIcon.PainterIcon(
                                painter = painterResource(id = R.drawable.play_light_icon)
                            ),
                            variant = ButtonVariant.BLACK,
                            customStyles = ButtonStyles(
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 7.dp)
                            )
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(size = 48.dp)
                            )
                            .padding(PaddingValues(horizontal = 26.dp, vertical = 12.dp))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(0.7f),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    trendingLabel,
                                    color = MaterialTheme.colorScheme.primary,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.inter_medium)),
                                        fontWeight = FontWeight(500)
                                    )
                                )
                                Text(
                                    title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight(
                                            600
                                        ),
                                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                                    ),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Row {
                                    Text(
                                        ageRating, color = RedColor,
                                        style = TextStyle(
                                            fontSize = 17.sp,
                                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                                            fontWeight = FontWeight(700)
                                        )
                                    )
                                    Text(
                                        " . ",
                                        color = MaterialTheme.colorScheme.primary,
                                        style = TextStyle(
                                            fontSize = 17.sp,
                                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                                            fontWeight = FontWeight(700)
                                        )
                                    )
                                    Text(
                                        language,
                                        color = MaterialTheme.colorScheme.primary,
                                        style = TextStyle(
                                            fontSize = 17.sp,
                                            fontFamily = FontFamily(Font(R.font.inter_bold)),
                                            fontWeight = FontWeight(700)
                                        )
                                    )
                                }
                                Text(genre, color = MaterialTheme.colorScheme.primary,style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFFFFFFF),

                                    ))
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(1f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                GradientButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = onBookClick,
                                    label = "Book"
                                )
                                Text(
                                    format,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight(
                                        500,
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    EntertainHubTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            MovieCard(
                title = "The Hobbit: An Unexpected Journey The Hobbit: An Unexpected Journey",
                imageUrl = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg",
                ageRating = "6+",
                language = "ENGLISH",
                genre = "FANTASY",
                format = "2D.3D.4DX",
                onWatchTrailerClick = {},
                onBookClick = {}
            )
        }
    }
}