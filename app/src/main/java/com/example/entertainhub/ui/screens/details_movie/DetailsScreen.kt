package com.example.entertainhub.ui.screens.details_movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.entertainhub.data.mock.MovieMockData
import com.example.entertainhub.ui.components.gradient_button.GradientButton
import com.example.entertainhub.ui.components.gradient_button.GradientButtonIcon
import com.example.entertainhub.ui.theme.customTypography
import kotlin.String


@Composable
private fun MovieInfoTextColumn(
    label: String,
    value: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Text(value, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun MovieInfoHeader(
    name: String,
    category: String,
    quality: String,
    onClickAction: () -> Unit
) {
    Row {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier
                .weight(1f)
        ) {
            Text(
                name,
                maxLines = 3,
                style = customTypography.bodyLarge.copy(fontWeight = FontWeight.W600)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    category.uppercase(),
                    style = MaterialTheme.typography.titleLarge.copy(
                        lineHeight = TextUnit.Unspecified,
                        fontWeight = FontWeight.W400
                    )
                )
                Text(
                    quality,
                    style = MaterialTheme.typography.bodySmall.copy(
                        lineHeight = TextUnit.Unspecified,
                        fontWeight = FontWeight.W500
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .width(160.dp)
        ) {
            GradientButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickAction,
                label = "Watch Trailer",
                endIcon = GradientButtonIcon.PainterIcon(painter = painterResource(R.drawable.play_light_icon))
            )
        }

    }
}


@Composable
private fun MovieDescription(description: String) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            "Story Plot",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500)
        )
        Text(description, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun MovieCast(images: List<String>) {
    var imagePreview by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Box {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            items(images.size) { index ->
                AsyncImage(
                    model = images[index],
                    modifier = Modifier
                        .size(63.dp, 70.dp)
                        .clip(RoundedCornerShape(17.dp))
                        .clickable {
                            imagePreview = images[index]
                            showDialog = true
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                )
            }
        }

        AnimatedVisibility(
            visible = showDialog,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Dialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.95f))
                        .clickable { showDialog = false }
                ) {
                    AsyncImage(
                        model = imagePreview,
                        contentDescription = "Enlarged Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentScale = ContentScale.Fit,
                        placeholder = debugPlaceholder(R.drawable.ic_launcher_background)
                    )

                    IconButton(
                        onClick = { showDialog = false },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .statusBarsPadding()
                            .background(
                                color = Color.White.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreenWithParallax(
    movieId: String,
    onBackClick: () -> Unit
) {
    val imageHeight = 400.dp
    val toolbarHeight = 100.dp
    val initialOverlap = 300.dp


    val density = LocalDensity.current
    val scrollState = rememberScrollState()

    val statusBarHeightPx = WindowInsets.statusBars.getTop(density)
    val toolbarHeightPx = with(density) { toolbarHeight.toPx() }
    val initialOverlapPx = with(density) { initialOverlap.toPx() }
    val maxImageScroll = with(density) { 100.dp.toPx() }

    val contentOffset by remember {
        derivedStateOf {
            val scrollPx = scrollState.value.toFloat()
            val minOffset = statusBarHeightPx + toolbarHeightPx
            (initialOverlapPx - scrollPx).coerceAtLeast(minOffset).toInt()
        }
    }

    val animatedOffset by animateIntAsState(
        targetValue = contentOffset,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "contentOffset"
    )

    val imageParallaxOffset by remember {
        derivedStateOf {
            val scrollPx = scrollState.value.toFloat()
            val limitedScroll = scrollPx.coerceAtMost(maxImageScroll)
            -(limitedScroll * 0.3f).toInt()
        }
    }

    val animatedImageParallaxOffset by animateIntAsState(
        targetValue = imageParallaxOffset,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "imageParallaxOffset"
    )


    SetSystemBarsColor(
        statusBarColor = Color.Transparent,
        darkIcons = false,
        restoreToTheme = true
    )
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val containerHeight = maxHeight - 200.dp
        MainScaffold(padding = PaddingValues(0.dp), bottomBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(10.dp)
                    .padding(bottom = 12.dp)
            ) {
                Button(onClick = {}, label = "Book Tickets", modifier = Modifier.fillMaxWidth())
            }
        }) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                ) {

                    AsyncImage(
                        model = MovieMockData.movieDetail.image,
                        contentDescription = movieId,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .offset { IntOffset(0, animatedImageParallaxOffset) }
                            .clip(
                                RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 55.dp,
                                    bottomEnd = 55.dp
                                )
                            ),
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

                // scroll box
                Box(
                    modifier = Modifier
                        .offset { IntOffset(0, animatedOffset) }
                        .padding(horizontal = 20.dp)
                        .height(containerHeight)
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(48.dp)
                        )


                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 15.dp, vertical = 30.dp)
                    ) {
                        MovieInfoHeader(
                            name = MovieMockData.movieDetail.name,
                            quality = MovieMockData.movieDetail.quality,
                            category = MovieMockData.movieDetail.category,
                            onClickAction = {}
                        )
                        Spacer(
                            Modifier
                                .height(1.dp)
                                .background(color = Color.White)
                                .fillMaxWidth()
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                MovieInfoTextColumn("Censor Rating", "A")
                                MovieInfoTextColumn("Duration", MovieMockData.movieDetail.duration)
                                MovieInfoTextColumn(
                                    label = "Release date",
                                    value = MovieMockData.movieDetail.date
                                )
                            }
                            MovieInfoTextColumn(
                                "Available in languages",
                                MovieMockData.movieDetail.language
                            )
                        }
                        Spacer(
                            Modifier
                                .height(1.dp)
                                .background(color = Color.White)
                                .fillMaxWidth()
                        )
                        MovieDescription(MovieMockData.movieDetail.description)
                        Spacer(
                            Modifier
                                .height(1.dp)
                                .background(color = Color.White)
                                .fillMaxWidth()
                        )
                        MovieCast(
                            images = MovieMockData.movieDetail.images
                        )
                    }

                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreenWithParallaxPreview() {
    EntertainHubTheme {
        MovieDetailScreenWithParallax(
            movieId = "hobbit",
            onBackClick = {}
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MovieCastPreview() {
//    EntertainHubTheme {
//        Scaffold { paddingValues ->
//            Box(modifier = Modifier.padding((paddingValues))) {
//                MovieCast(
//                    MovieMockData.movieDetail.images,
//                )
//            }
//        }
//    }
//}
