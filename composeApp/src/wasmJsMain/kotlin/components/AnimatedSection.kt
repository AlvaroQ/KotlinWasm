package components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntOffset

enum class AnimationType {
    FADE_IN,
    SLIDE_UP,
    SLIDE_LEFT,
    SLIDE_RIGHT,
    SCALE_IN
}

val LocalScrollPosition = compositionLocalOf { 0 }
val LocalViewportHeight = compositionLocalOf { 800 }

@Composable
fun AnimatedSection(
    animationType: AnimationType = AnimationType.FADE_IN,
    delay: Int = 0,
    threshold: Float = 0.8f,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var sectionTop by remember { mutableStateOf(0) }
    var hasAnimated by remember { mutableStateOf(false) }

    // Only read scroll position if we haven't animated yet (performance optimization)
    if (!hasAnimated) {
        val scrollPosition = LocalScrollPosition.current
        val viewportHeight = LocalViewportHeight.current

        // Determine if section is in viewport
        val isInViewport = remember(scrollPosition, sectionTop, viewportHeight) {
            val visibleThreshold = viewportHeight * threshold
            sectionTop < scrollPosition + viewportHeight - visibleThreshold
        }

        // Once visible, mark as animated (don't re-animate)
        LaunchedEffect(isInViewport) {
            if (isInViewport) {
                hasAnimated = true
            }
        }
    }

    val shouldAnimate = hasAnimated

    // Animation values
    val animatedAlpha by animateFloatAsState(
        targetValue = if (shouldAnimate) 1f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = delay,
            easing = FastOutSlowInEasing
        )
    )

    val animatedOffsetY by animateIntAsState(
        targetValue = if (shouldAnimate) 0 else when (animationType) {
            AnimationType.SLIDE_UP -> 80
            else -> 0
        },
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = delay,
            easing = FastOutSlowInEasing
        )
    )

    val animatedOffsetX by animateIntAsState(
        targetValue = if (shouldAnimate) 0 else when (animationType) {
            AnimationType.SLIDE_LEFT -> -80
            AnimationType.SLIDE_RIGHT -> 80
            else -> 0
        },
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = delay,
            easing = FastOutSlowInEasing
        )
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (shouldAnimate) 1f else 0.9f,
        animationSpec = tween(
            durationMillis = 600,
            delayMillis = delay,
            easing = FastOutSlowInEasing
        )
    )

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                sectionTop = coordinates.positionInRoot().y.toInt()
            }
            .alpha(animatedAlpha)
            .offset { IntOffset(animatedOffsetX, animatedOffsetY) }
            .then(
                if (animationType == AnimationType.SCALE_IN) {
                    Modifier.graphicsLayer(
                        scaleX = animatedScale,
                        scaleY = animatedScale
                    )
                } else Modifier
            )
    ) {
        content()
    }
}
