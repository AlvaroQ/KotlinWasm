package theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import personalpage.composeapp.generated.resources.Res
import personalpage.composeapp.generated.resources.roboto_serif_regular

// Cyberpunk Color Palette
object CyberpunkColors {
    val NeonCyan = Color(0xFF00FFFF)
    val NeonMagenta = Color(0xFFFF00FF)
    val NeonPurple = Color(0xFF8B00FF)
    val NeonPink = Color(0xFFFF0080)
    val NeonGreen = Color(0xFF39FF14)
    val NeonYellow = Color(0xFFFFFF00)

    val DarkBackground = Color(0xFF0A0A0F)
    val DarkSurface = Color(0xFF12121A)
    val DarkCard = Color(0xFF1A1A2E)
    val GridLines = Color(0xFF16213E)

    val TextPrimary = Color(0xFFE0E0E0)
    val TextSecondary = Color(0xFFB0B0B0)

    val GlowCyan = Color(0x6600FFFF)
    val GlowMagenta = Color(0x66FF00FF)
}

val CyberpunkColorScheme = darkColors(
    primary = CyberpunkColors.NeonCyan,
    primaryVariant = CyberpunkColors.NeonPurple,
    secondary = CyberpunkColors.NeonMagenta,
    secondaryVariant = CyberpunkColors.NeonPink,
    background = CyberpunkColors.DarkBackground,
    surface = CyberpunkColors.DarkSurface,
    error = Color(0xFFFF4444),
    onPrimary = CyberpunkColors.DarkBackground,
    onSecondary = CyberpunkColors.DarkBackground,
    onBackground = CyberpunkColors.TextPrimary,
    onSurface = CyberpunkColors.TextPrimary,
    onError = Color.White
)

@Composable
fun CyberpunkTypography(): Typography {
    return Typography(
        h1 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 72.sp,
            letterSpacing = 4.sp
        ),
        h2 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            letterSpacing = 2.sp
        ),
        h3 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            letterSpacing = 1.sp
        ),
        h4 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp
        ),
        h5 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        ),
        h6 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        ),
        body1 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        body2 = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        button = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            letterSpacing = 2.sp
        ),
        caption = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    )
}

@Composable
fun CyberpunkTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = CyberpunkColorScheme,
        typography = CyberpunkTypography(),
        content = content
    )
}

// Animated gradient background
@Composable
fun CyberpunkBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CyberpunkColors.DarkBackground,
                        CyberpunkColors.DarkSurface,
                        Color(0xFF0D0D1A),
                        CyberpunkColors.DarkBackground
                    )
                )
            )
            .drawBehind {
                // Grid lines effect
                val gridSpacing = 50f
                val gridColor = CyberpunkColors.GridLines.copy(alpha = 0.3f)

                // Horizontal lines
                var y = 0f
                while (y < size.height) {
                    drawLine(
                        color = gridColor,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = 1f
                    )
                    y += gridSpacing
                }

                // Vertical lines
                var x = 0f
                while (x < size.width) {
                    drawLine(
                        color = gridColor,
                        start = Offset(x, 0f),
                        end = Offset(x, size.height),
                        strokeWidth = 1f
                    )
                    x += gridSpacing
                }
            }
    ) {
        content()
    }
}

// Neon glow effect modifier
fun Modifier.neonGlow(
    color: Color = CyberpunkColors.NeonCyan,
    radius: Float = 20f
): Modifier = this.drawBehind {
    drawCircle(
        color = color.copy(alpha = 0.3f),
        radius = size.minDimension / 2 + radius
    )
}

// Animated neon border
@Composable
fun rememberNeonAnimation(): State<Float> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
}
