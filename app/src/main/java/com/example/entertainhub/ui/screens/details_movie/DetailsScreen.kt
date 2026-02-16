package com.example.entertainhub.ui.screens.details_movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreenWithParallax(
    movieId: String,
    onBackClick: () -> Unit
) {
    val density = LocalDensity.current
    val scrollState = rememberScrollState()
    val statusBarHeightPx = WindowInsets.statusBars.getTop(density)
    val toolbarHeightPx = with(density) { 73.dp.toPx() }
    val initialOverlap = 145.dp
    val initialOverlapPx = with(LocalDensity.current) { initialOverlap.toPx() }

    val contentOffset by remember {
        derivedStateOf {
            val scrollPx = scrollState.value.toFloat()
            val minOffset = statusBarHeightPx + toolbarHeightPx
            (initialOverlapPx - scrollPx).coerceAtLeast(minOffset).toInt()
        }
    }

//    val isCollapsed by remember {
//        derivedStateOf {
//            contentOffset <= (statusBarHeightPx + toolbarHeightPx).toInt()
//        }
//    }
//
//    val shape = if (isCollapsed) {
//        RoundedCornerShape(0.dp)
//    } else {
//        RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
//    }
//
//    val animatedRadius by animateDpAsState(
//        targetValue = if (isCollapsed) 0.dp else 48.dp,
//        label = ""
//    )

    SetSystemBarsColor(
        statusBarColor = Color.Transparent,
        darkIcons = false,
        restoreToTheme = true
    )
    MainScaffold(padding = PaddingValues(0.dp) , bottomBar = {
        Button(onClick = {}, label = "Book Tickets", modifier = Modifier.fillMaxWidth())
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

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
                        .height(400.dp)
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .offset { IntOffset(0, contentOffset) }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Spacer(modifier = Modifier.height(initialOverlap))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(48.dp)
                            )

                    ) {
                        repeat(60) {
                            Text("aaa ${it}", fontSize = 40.sp)
                        }
                    }
                }

            }

            // Watch Trailer Button


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
