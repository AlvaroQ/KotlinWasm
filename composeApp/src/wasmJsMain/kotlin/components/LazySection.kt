package components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.CyberpunkThemeColors

/**
 * Lazy section component that defers rendering until near viewport.
 * This prioritizes above-the-fold content by not rendering below-fold sections initially.
 *
 * @param minHeight Minimum height for placeholder (prevents layout shifts)
 * @param threshold How far from viewport to start rendering (1.0 = viewport height)
 * @param content The actual section content to render when visible
 */
@Composable
fun LazySection(
    modifier: Modifier = Modifier,
    minHeight: Dp = 400.dp,
    threshold: Float = 1.5f, // Start rendering 1.5 viewport heights before visible
    content: @Composable () -> Unit
) {
    var sectionTop by remember { mutableStateOf(Int.MAX_VALUE) }
    var shouldRender by remember { mutableStateOf(false) }

    // Only check visibility if not already rendering
    if (!shouldRender) {
        val scrollPosition = LocalScrollPosition.current
        val viewportHeight = LocalViewportHeight.current

        val isNearViewport = remember(scrollPosition, sectionTop, viewportHeight) {
            val buffer = (viewportHeight * threshold).toInt()
            sectionTop < scrollPosition + viewportHeight + buffer
        }

        LaunchedEffect(isNearViewport) {
            if (isNearViewport) {
                shouldRender = true
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                if (sectionTop == Int.MAX_VALUE) {
                    sectionTop = coordinates.positionInRoot().y.toInt()
                }
            }
    ) {
        if (shouldRender) {
            content()
        } else {
            // Animated skeleton placeholder
            SkeletonPlaceholder(minHeight = minHeight)
        }
    }
}

/**
 * Animated skeleton placeholder with shimmer effect
 */
@Composable
private fun SkeletonPlaceholder(minHeight: Dp) {
    val infiniteTransition = rememberInfiniteTransition()

    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val baseColor = CyberpunkThemeColors.card
    val shimmerColor = CyberpunkThemeColors.neonCyan.copy(alpha = 0.08f)

    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            baseColor,
            shimmerColor,
            baseColor
        ),
        start = Offset(shimmerOffset - 500f, 0f),
        end = Offset(shimmerOffset, 0f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(minHeight)
            .background(CyberpunkThemeColors.background)
            .padding(horizontal = 20.dp, vertical = 40.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title skeleton
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(shimmerBrush)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Content skeletons
            repeat(3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(shimmerBrush)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Small cards row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(shimmerBrush)
                    )
                }
            }
        }
    }
}
