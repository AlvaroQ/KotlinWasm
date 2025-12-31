package components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.CyberpunkThemeColors

/**
 * Lazy loading image component that only loads when visible in viewport.
 * Uses the same visibility detection as AnimatedSection.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun LazyImage(
    resource: DrawableResource,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    threshold: Float = 0.9f,  // Load slightly before fully visible
    fadeInDuration: Int = 400
) {
    var imageTop by remember { mutableStateOf(0) }
    var shouldLoad by remember { mutableStateOf(false) }

    // Only read scroll position if we haven't loaded yet (performance optimization)
    if (!shouldLoad) {
        val scrollPosition = LocalScrollPosition.current
        val viewportHeight = LocalViewportHeight.current

        // Determine if image is near viewport
        val isNearViewport = remember(scrollPosition, imageTop, viewportHeight) {
            val buffer = viewportHeight * threshold
            imageTop < scrollPosition + viewportHeight + buffer &&
            imageTop > scrollPosition - buffer
        }

        // Once near viewport, start loading
        LaunchedEffect(isNearViewport) {
            if (isNearViewport) {
                shouldLoad = true
            }
        }
    }

    // Fade in animation
    val alpha by animateFloatAsState(
        targetValue = if (shouldLoad) 1f else 0f,
        animationSpec = tween(
            durationMillis = fadeInDuration,
            easing = FastOutSlowInEasing
        )
    )

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                imageTop = coordinates.positionInRoot().y.toInt()
            }
    ) {
        if (shouldLoad) {
            Image(
                painter = painterResource(resource),
                contentDescription = contentDescription,
                modifier = Modifier
                    .matchParentSize()
                    .alpha(alpha),
                contentScale = contentScale,
                alignment = alignment
            )
        } else {
            // Placeholder while not loaded
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(CyberpunkThemeColors.card)
            )
        }
    }
}

/**
 * Lazy loading image for logos (smaller, with rounded corners)
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun LazyLogo(
    resource: DrawableResource,
    contentDescription: String?,
    size: Dp = 48.dp,
    modifier: Modifier = Modifier
) {
    LazyImage(
        resource = resource,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Fit,
        threshold = 1.2f  // Load logos a bit earlier
    )
}
