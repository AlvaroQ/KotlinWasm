package theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
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

// Theme Mode
enum class ThemeMode { DARK, LIGHT }

val LocalThemeMode = compositionLocalOf { ThemeMode.DARK }

// Dark Cyberpunk Color Palette
object CyberpunkColors {
    // Neon accent colors
    val NeonCyan = Color(0xFF00FFFF)
    val NeonMagenta = Color(0xFFFF00FF)
    val NeonPurple = Color(0xFF8B00FF)
    val NeonPink = Color(0xFFFF0080)
    val NeonGreen = Color(0xFF39FF14)
    val NeonYellow = Color(0xFFFFFF00)

    // Brand/custom colors
    val Orchid = Color(0xFFDA70D6)
    val SantanderBlue = Color(0xFF4A90D9)

    // Dark backgrounds
    val DarkBackground = Color(0xFF0A0A0F)
    val DarkSurface = Color(0xFF12121A)
    val DarkCard = Color(0xFF1A1A2E)
    val DarkGradient = Color(0xFF0A0A1A)
    val GridLines = Color(0xFF16213E)

    // Text colors
    val TextPrimary = Color(0xFFE0E0E0)
    val TextSecondary = Color(0xFFB0B0B0)

    // Glow effects
    val GlowCyan = Color(0x6600FFFF)
    val GlowMagenta = Color(0x66FF00FF)

    // Logo background (almost black for dark theme)
    val LogoBackground = Color(0xFF1A1A1A)
}

// Light Cyberpunk Color Palette
object CyberpunkColorsLight {
    // Neon accent colors (much darker for high contrast on light bg)
    val NeonCyan = Color(0xFF005555)      // Darker cyan for readability
    val NeonMagenta = Color(0xFF990099)   // Darker magenta
    val NeonPurple = Color(0xFF5500AA)    // Darker purple
    val NeonPink = Color(0xFFAA0055)      // Darker pink
    val NeonGreen = Color(0xFF0D6B0D)     // Darker green
    val NeonYellow = Color(0xFF997700)    // Darker yellow/gold

    // Brand/custom colors (darker for light theme)
    val Orchid = Color(0xFF8B4088)        // Much darker orchid
    val SantanderBlue = Color(0xFF2A60A9) // Darker blue

    // Light backgrounds
    val LightBackground = Color(0xFFF5F5FA)
    val LightSurface = Color(0xFFEAEAF2)
    val LightCard = Color(0xFFFFFFFF)
    val LightGradient = Color(0xFFE5E5F0)
    val GridLines = Color(0xFFD0D0E0)

    // Text colors (high contrast)
    val TextPrimary = Color(0xFF0A0A1E)   // Almost black
    val TextSecondary = Color(0xFF3A3A4A) // Dark gray

    // Glow effects
    val GlowCyan = Color(0x44007777)
    val GlowMagenta = Color(0x44990099)

    // Logo background (almost white for light theme)
    val LogoBackground = Color(0xFFF5F5F5)
}

// Theme-aware color accessor
object CyberpunkThemeColors {
    val neonCyan: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.NeonCyan else CyberpunkColorsLight.NeonCyan

    val neonMagenta: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.NeonMagenta else CyberpunkColorsLight.NeonMagenta

    val neonPurple: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.NeonPurple else CyberpunkColorsLight.NeonPurple

    val neonPink: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.NeonPink else CyberpunkColorsLight.NeonPink

    val neonGreen: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.NeonGreen else CyberpunkColorsLight.NeonGreen

    val neonYellow: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.NeonYellow else CyberpunkColorsLight.NeonYellow

    val background: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.DarkBackground else CyberpunkColorsLight.LightBackground

    val surface: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.DarkSurface else CyberpunkColorsLight.LightSurface

    val card: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.DarkCard else CyberpunkColorsLight.LightCard

    val gridLines: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.GridLines else CyberpunkColorsLight.GridLines

    val textPrimary: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.TextPrimary else CyberpunkColorsLight.TextPrimary

    val textSecondary: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.TextSecondary else CyberpunkColorsLight.TextSecondary

    val glowCyan: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.GlowCyan else CyberpunkColorsLight.GlowCyan

    val glowMagenta: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.GlowMagenta else CyberpunkColorsLight.GlowMagenta

    val logoBackground: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.LogoBackground else CyberpunkColorsLight.LogoBackground

    val santanderBlue: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.SantanderBlue else CyberpunkColorsLight.SantanderBlue

    val orchid: Color
        @Composable get() = if (LocalThemeMode.current == ThemeMode.DARK) CyberpunkColors.Orchid else CyberpunkColorsLight.Orchid
}

val CyberpunkColorSchemeDark = darkColors(
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

val CyberpunkColorSchemeLight = lightColors(
    primary = CyberpunkColorsLight.NeonCyan,
    primaryVariant = CyberpunkColorsLight.NeonPurple,
    secondary = CyberpunkColorsLight.NeonMagenta,
    secondaryVariant = CyberpunkColorsLight.NeonPink,
    background = CyberpunkColorsLight.LightBackground,
    surface = CyberpunkColorsLight.LightSurface,
    error = Color(0xFFCC3333),
    onPrimary = CyberpunkColorsLight.LightBackground,
    onSecondary = CyberpunkColorsLight.LightBackground,
    onBackground = CyberpunkColorsLight.TextPrimary,
    onSurface = CyberpunkColorsLight.TextPrimary,
    onError = Color.White
)

// For backward compatibility
val CyberpunkColorScheme = CyberpunkColorSchemeDark

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
fun CyberpunkTheme(
    themeMode: ThemeMode = ThemeMode.DARK,
    content: @Composable () -> Unit
) {
    val colors = when (themeMode) {
        ThemeMode.DARK -> CyberpunkColorSchemeDark
        ThemeMode.LIGHT -> CyberpunkColorSchemeLight
    }

    CompositionLocalProvider(LocalThemeMode provides themeMode) {
        MaterialTheme(
            colors = colors,
            typography = CyberpunkTypography(),
            content = content
        )
    }
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
