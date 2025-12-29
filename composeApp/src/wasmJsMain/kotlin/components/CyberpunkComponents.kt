package components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import theme.CyberpunkThemeColors
import kotlin.random.Random

// Typewriter Effect Text
@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.h2,
    color: Color = CyberpunkThemeColors.neonCyan,
    typingSpeed: Long = 100L,
    startDelay: Long = 500L,
    showCursor: Boolean = true
) {
    var displayedText by remember { mutableStateOf("") }
    var showCursorState by remember { mutableStateOf(true) }

    LaunchedEffect(text) {
        delay(startDelay)
        text.forEachIndexed { index, _ ->
            displayedText = text.substring(0, index + 1)
            delay(typingSpeed)
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            showCursorState = !showCursorState
        }
    }

    Text(
        text = displayedText + if (showCursor && showCursorState) "_" else if (showCursor) " " else "",
        modifier = modifier,
        style = style,
        color = color
    )
}

// Glitch Effect Text
@Composable
fun GlitchText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.h1,
    primaryColor: Color = CyberpunkThemeColors.neonCyan,
    glitchColor1: Color = CyberpunkThemeColors.neonMagenta,
    glitchColor2: Color = CyberpunkThemeColors.neonGreen
) {
    val infiniteTransition = rememberInfiniteTransition()

    val glitchOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                0f at 0
                0f at 2700
                1f at 2750
                0f at 2800
                1f at 2850
                0f at 2900
                0f at 3000
            },
            repeatMode = RepeatMode.Restart
        )
    )

    Box(modifier = modifier) {
        // Glitch layer 1 (cyan offset)
        if (glitchOffset > 0.5f) {
            Text(
                text = text,
                style = style,
                color = glitchColor1.copy(alpha = 0.7f),
                modifier = Modifier.offset(x = (-3).dp, y = 0.dp)
            )
        }

        // Glitch layer 2 (magenta offset)
        if (glitchOffset > 0.5f) {
            Text(
                text = text,
                style = style,
                color = glitchColor2.copy(alpha = 0.7f),
                modifier = Modifier.offset(x = 3.dp, y = 0.dp)
            )
        }

        // Main text
        Text(
            text = text,
            style = style,
            color = primaryColor
        )
    }
}

// Neon Card Component
@Composable
fun NeonCard(
    modifier: Modifier = Modifier,
    glowColor: Color = CyberpunkThemeColors.neonCyan,
    backgroundColor: Color = CyberpunkThemeColors.card,
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val animatedGlow by animateFloatAsState(
        targetValue = if (isHovered) 1f else 0.5f,
        animationSpec = tween(300)
    )

    val animatedBorderWidth by animateDpAsState(
        targetValue = if (isHovered) 2.dp else 1.dp,
        animationSpec = tween(300)
    )

    Column(
        modifier = modifier
            .hoverable(interactionSource)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(
                width = animatedBorderWidth,
                brush = Brush.linearGradient(
                    colors = listOf(
                        glowColor.copy(alpha = animatedGlow),
                        glowColor.copy(alpha = animatedGlow * 0.5f)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .drawBehind {
                if (isHovered) {
                    drawRoundRect(
                        color = glowColor.copy(alpha = 0.1f),
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
            }
            .padding(16.dp),
        content = content
    )
}

// Skill Bar with neon effect
@Composable
fun NeonSkillBar(
    skill: String,
    level: Float, // 0f to 1f
    modifier: Modifier = Modifier,
    barColor: Color = CyberpunkThemeColors.neonCyan,
    animationDelay: Int = 0
) {
    var animatedLevel by remember { mutableStateOf(0f) }

    LaunchedEffect(level) {
        delay(animationDelay.toLong())
        animatedLevel = level
    }

    val animatedValue by animateFloatAsState(
        targetValue = animatedLevel,
        animationSpec = tween(1500, easing = FastOutSlowInEasing)
    )

    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = skill,
                style = MaterialTheme.typography.body1,
                color = CyberpunkThemeColors.textPrimary
            )
            Text(
                text = "${(animatedValue * 100).toInt()}%",
                style = MaterialTheme.typography.body2,
                color = barColor
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(CyberpunkThemeColors.card)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedValue)
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                barColor.copy(alpha = 0.8f),
                                barColor
                            )
                        )
                    )
                    .drawBehind {
                        drawRoundRect(
                            color = barColor.copy(alpha = 0.5f),
                            cornerRadius = CornerRadius(4.dp.toPx())
                        )
                    }
            )
        }
    }
}

// Terminal-style text display
@Composable
fun TerminalText(
    lines: List<String>,
    modifier: Modifier = Modifier,
    promptColor: Color = CyberpunkThemeColors.neonGreen,
    textColor: Color = CyberpunkThemeColors.textPrimary
) {
    var visibleLines by remember { mutableStateOf(0) }

    LaunchedEffect(lines) {
        lines.forEachIndexed { index, _ ->
            delay(300)
            visibleLines = index + 1
        }
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0D0D0D))
            .border(1.dp, CyberpunkThemeColors.gridLines, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Terminal header
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFFF5F56))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFFFBD2E))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF27CA40))
            )
        }

        // Terminal content
        lines.take(visibleLines).forEach { line ->
            Row {
                Text(
                    text = "> ",
                    color = promptColor,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = line,
                    color = textColor,
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Blinking cursor
        var showCursor by remember { mutableStateOf(true) }
        LaunchedEffect(Unit) {
            while (true) {
                delay(500)
                showCursor = !showCursor
            }
        }

        if (visibleLines >= lines.size) {
            Text(
                text = if (showCursor) "> _" else ">  ",
                color = promptColor,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

// Animated Section Title
@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = CyberpunkThemeColors.neonCyan
) {
    val infiniteTransition = rememberInfiniteTransition()

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "< $title />",
            style = MaterialTheme.typography.h3,
            color = color.copy(alpha = glowAlpha),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Animated line under title
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(2.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            color.copy(alpha = glowAlpha),
                            Color.Transparent
                        )
                    )
                )
        )
    }
}

// Floating orb animation (simulates 3D element)
@Composable
fun FloatingOrb(
    modifier: Modifier = Modifier,
    color: Color = CyberpunkThemeColors.neonCyan,
    size: Dp = 100.dp
) {
    val infiniteTransition = rememberInfiniteTransition()

    val offsetY by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .size(size)
            .offset(y = offsetY.dp)
            .drawBehind {
                // Outer glow
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            color.copy(alpha = 0.3f),
                            color.copy(alpha = 0.1f),
                            Color.Transparent
                        )
                    ),
                    radius = this.size.minDimension * scale
                )

                // Inner orb
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            color.copy(alpha = 0.9f),
                            color.copy(alpha = 0.5f),
                            color.copy(alpha = 0.2f)
                        )
                    ),
                    radius = this.size.minDimension / 2 * 0.6f
                )

                // Highlight
                drawCircle(
                    color = Color.White.copy(alpha = 0.3f),
                    radius = this.size.minDimension / 2 * 0.2f,
                    center = Offset(
                        this.size.width / 2 - this.size.width / 6,
                        this.size.height / 2 - this.size.height / 6
                    )
                )
            }
    )
}