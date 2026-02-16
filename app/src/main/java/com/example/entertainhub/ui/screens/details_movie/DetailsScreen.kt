package com.example.entertainhub.ui.screens.details_movie
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.entertainhub.ui.components.scaffolds.MainScaffold
import com.example.entertainhub.ui.theme.EntertainHubTheme
import com.example.entertainhub.utils.SetSystemBarsColor
import com.example.entertainhub.utils.debugPlaceholder
import com.example.entertainhub.R
import com.example.entertainhub.ui.components.button.Button
import com.example.entertainhub.ui.components.button.ButtonIcon
import com.example.entertainhub.ui.components.button.ButtonStyles
import com.example.entertainhub.ui.components.button.ButtonVariant

/*
@Composable
fun MovieDetailScreenWithParallax(
    movieId: String,
    onBackClick: () -> Unit
) {
    val scrollState = rememberLazyListState()

    // ✅ Вычисляем offset для параллакса
    val imageOffset = remember {
        derivedStateOf {
            val offset = scrollState.firstVisibleItemScrollOffset
            (offset * 0.5f).toInt()  // Параллакс эффект (медленнее скроллится)
        }
    }

    SetSystemBarsColor(
        statusBarColor = Color.Transparent,
        darkIcons = false,
        restoreToTheme = true
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) {
                    // ✅ Изображение с параллаксом
                    AsyncImage(
                        model = "https://...",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)  // Чуть больше чем Box
                            .offset { IntOffset(0, -imageOffset.value) },  // Параллакс!
                        contentScale = ContentScale.Crop
                    )

                    // Градиент
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                    startY = 200f
                                )
                            )
                    )

                    // Контент поверх
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Text(
                            "EVIL DEAD RISE",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            "HORROR • 2D 3D 4DX",
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // Watch Trailer Button
            item {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.White)
                ) {
                    Text("Watch Trailer")
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Default.PlayCircle, null, Modifier.size(20.dp))
                }
            }

            // Info Grid
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MovieInfoColumn("Censor Rating", "A")
                    MovieInfoColumn("Duration", "1hr38min")
                    MovieInfoColumn("Release date", "21 April 2023")
                }
            }

            item { Divider(Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) }

            // Languages
            item {
                MovieSection(
                    label = "Available in languages",
                    value = "English"
                )
            }

            item { Spacer(Modifier.height(24.dp)) }

            // Story Plot
            item {
                Column(Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        "Story Plot",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Evil Dead is a 2013 American supernatural horror film directed by Fede Alvarez, who co-wrote the screenplay with Rodo Sayagues. The film is the fourth installment in the Evil Dead franchise and serves as a soft reboot of the series. Dubbed a \"re-imagining\" of the original The Evil Dead, the film follows a group of friends who are possessed by demons after discovering the Book of the Dead in a remote forest cabin.",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        lineHeight = 20.sp
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }

            // Cast
            item {
                Column(Modifier.padding(start = 20.dp)) {
                    Text(
                        "Cast",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(Modifier.height(12.dp))
                }
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(6) { index ->
                        CastMember(
                            name = "Actor ${index + 1}",
                            imageUrl = ""
                        )
                    }
                }
            }

            // Bottom spacing
            item {
                Spacer(
                    Modifier
                        .height(80.dp)
                        .navigationBarsPadding()
                )
            }
        }

        // Navigation buttons (fixed)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Black.copy(0.5f), CircleShape)
            ) {
                Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
            }

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Black.copy(0.5f), CircleShape)
            ) {
                Icon(Icons.Default.MoreVert, "Menu", tint = Color.White)
            }
        }
    }
}

private fun BoxScope.item(function: () -> Unit) {}

@Composable
private fun MovieInfoColumn(label: String, value: String) {
    Column {
        Text(
            label,
            fontSize = 12.sp,
            color = Color.White.copy(0.6f)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Composable
private fun MovieSection(label: String, value: String) {
    Column(Modifier.padding(horizontal = 20.dp)) {
        Text(
            label,
            fontSize = 12.sp,
            color = Color.White.copy(0.6f)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            value,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Composable
private fun CastMember(name: String, imageUrl: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            name,
            fontSize = 12.sp,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreenWithParallax(
    movieId: String,
    onBackClick: () -> Unit
) {
    val scrollState = rememberLazyListState()
    val imageOffset = remember {
        derivedStateOf {
            val offset = scrollState.firstVisibleItemScrollOffset
            (offset * 0.5f).toInt()
        }
    }
    SetSystemBarsColor(
        statusBarColor = Color.Transparent,
        darkIcons = false,
        restoreToTheme = true
    )
    MainScaffold(padding = PaddingValues(0.dp)){
        paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    ) {

                        AsyncImage(
                            model = "https://i.pinimg.com/736x/f2/f5/ed/f2f5ed5520b877478784a8cbd6828e57.jpg",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp).clip(RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 55.dp,
                                    bottomEnd = 55.dp
                                ))
                                .offset { IntOffset(0, -imageOffset.value) },
                            contentScale = ContentScale.Crop,
                            placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = paddingValues.calculateTopPadding() + 13.dp)
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .height(50.dp)
                                .align(Alignment.TopStart)

                        ) {
                            Button(
                                onClick = onBackClick,
                                startIcon = ButtonIcon.PainterIcon(
                                    painterResource(id = R.drawable.expand_right_light),

                                    ),
                                variant = ButtonVariant.BLACK,
                                modifier = Modifier.size(50.dp),
                                customStyles = ButtonStyles(
                                    radius = 50.dp,
                                    contentPadding = PaddingValues(0.dp)
                                )
                            )
                        }
                    }
                }}
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreenWithParallaxPreview() {
    EntertainHubTheme {
        MovieDetailScreenWithParallax(
            movieId = "123",
            onBackClick = {}
        )
    }
}
