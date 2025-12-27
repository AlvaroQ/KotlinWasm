import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.SectionTitle
import theme.CyberpunkColors

@Composable
fun SectionAIProjects() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < 900
    // Usar columna hasta pantallas muy grandes (>1400px)
    val useColumnLayout = screenWidth < 1400

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CyberpunkColors.DarkBackground,
                        Color(0xFF0A0A1A),
                        CyberpunkColors.DarkBackground
                    )
                )
            )
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section header with special styling
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = CyberpunkColors.NeonMagenta,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "< LATEST AI PROJECTS />",
                style = MaterialTheme.typography.h3,
                color = CyberpunkColors.NeonMagenta
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = CyberpunkColors.NeonMagenta,
                modifier = Modifier.size(32.dp)
            )
        }

        Text(
            text = "Showcasing my latest AI-powered applications",
            style = MaterialTheme.typography.body1,
            color = CyberpunkColors.TextSecondary,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        if (useColumnLayout) {
            // Column layout - vertical stack
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (isMobile) 0.dp else 60.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AIProjectCard(
                    title = "Translation & Voice AI",
                    subtitle = "100% Local Processing",
                    description = "Desktop app that converts documents (PDF, Word, TXT) into high-quality speech using local AI models. No cloud, no costs, full privacy.",
                    features = listOf(
                        "50+ Neural Voices",
                        "200+ Languages",
                        "Offline Processing",
                        "Document Support"
                    ),
                    techStack = listOf("Python", "PyTorch", "ONNX", "Kokoro-82M", "NLLB-200"),
                    accentColor = CyberpunkColors.NeonCyan,
                    githubUrl = "github.com/AlvaroQ/TranslationAndVoiceLocally",
                    useFullWidth = true
                )

                AIProjectCard(
                    title = "Financial AI Platform",
                    subtitle = "Real-time Market Intelligence",
                    description = "Full-stack platform integrating AI agents for financial analysis. Combines news search with technical chart analysis for investors.",
                    features = listOf(
                        "Real-time News AI",
                        "Chart Analysis",
                        "Pattern Detection",
                        "Technical Indicators"
                    ),
                    techStack = listOf("Next.js", "Perplexity AI", "Gemini 2.0", "TypeScript"),
                    accentColor = CyberpunkColors.NeonGreen,
                    githubUrl = "github.com/AlvaroQ/ProjectIA",
                    useFullWidth = true
                )
            }
        } else {
            // Row layout for larger screens - cards con weight para adaptarse
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                AIProjectCard(
                    modifier = Modifier.weight(1f),
                    title = "Translation & Voice AI",
                    subtitle = "100% Local Processing",
                    description = "Desktop app that converts documents (PDF, Word, TXT) into high-quality speech using local AI models. No cloud, no costs, full privacy.",
                    features = listOf(
                        "50+ Neural Voices",
                        "200+ Languages",
                        "Offline Processing",
                        "Document Support"
                    ),
                    techStack = listOf("Python", "PyTorch", "ONNX", "Kokoro-82M", "NLLB-200"),
                    accentColor = CyberpunkColors.NeonCyan,
                    githubUrl = "github.com/AlvaroQ/TranslationAndVoiceLocally"
                )

                AIProjectCard(
                    modifier = Modifier.weight(1f),
                    title = "Financial AI Platform",
                    subtitle = "Real-time Market Intelligence",
                    description = "Full-stack platform integrating AI agents for financial analysis. Combines news search with technical chart analysis for investors.",
                    features = listOf(
                        "Real-time News AI",
                        "Chart Analysis",
                        "Pattern Detection",
                        "Technical Indicators"
                    ),
                    techStack = listOf("Next.js", "Perplexity AI", "Gemini 2.0", "TypeScript"),
                    accentColor = CyberpunkColors.NeonGreen,
                    githubUrl = "github.com/AlvaroQ/ProjectIA"
                )
            }
        }

        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

    }
}

@Composable
private fun AIProjectCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    description: String,
    features: List<String>,
    techStack: List<String>,
    accentColor: Color,
    githubUrl: String,
    useFullWidth: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val glowAlpha by animateFloatAsState(
        targetValue = if (isHovered) 0.4f else 0.1f,
        animationSpec = tween(300)
    )

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = tween(300)
    )

    Column(
        modifier = modifier
            .then(if (useFullWidth) Modifier.fillMaxWidth().widthIn(max = 700.dp) else Modifier)
            .hoverable(interactionSource)
            .drawBehind {
                // Glow effect
                drawRoundRect(
                    color = accentColor.copy(alpha = glowAlpha),
                    cornerRadius = CornerRadius(16.dp.toPx()),
                    size = size.copy(
                        width = size.width + 20,
                        height = size.height + 20
                    ),
                    topLeft = Offset(-10f, -10f)
                )
            }
            .clip(RoundedCornerShape(16.dp))
            .background(CyberpunkColors.DarkCard)
            .border(
                width = if (isHovered) 2.dp else 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(accentColor, accentColor.copy(alpha = 0.5f))
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(24.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    color = accentColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.caption,
                    color = CyberpunkColors.TextSecondary
                )
            }

            // AI Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(accentColor.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "AI",
                    style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                    color = accentColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = description,
            style = MaterialTheme.typography.body2,
            color = CyberpunkColors.TextPrimary,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Features grid
        Text(
            text = "KEY FEATURES",
            style = MaterialTheme.typography.caption.copy(letterSpacing = 2.sp),
            color = CyberpunkColors.TextSecondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                features.take(2).forEach { feature ->
                    FeatureItem(feature, accentColor)
                }
            }
            Column {
                features.drop(2).forEach { feature ->
                    FeatureItem(feature, accentColor)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tech stack
        Text(
            text = "TECH STACK",
            style = MaterialTheme.typography.caption.copy(letterSpacing = 2.sp),
            color = CyberpunkColors.TextSecondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            techStack.forEach { tech ->
                TechChip(tech, accentColor)
                Spacer(modifier = Modifier.width(6.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // GitHub link
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(accentColor.copy(alpha = 0.1f))
                .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "> $githubUrl",
                style = MaterialTheme.typography.caption,
                color = accentColor
            )
        }
    }
}

@Composable
private fun FeatureItem(text: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = CyberpunkColors.TextPrimary
        )
    }
}

@Composable
private fun TechChip(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
            color = color
        )
    }
}

@Composable
private fun StatItem(value: String, label: String, color: Color) {
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.h2.copy(fontSize = 48.sp),
            color = color.copy(alpha = glowAlpha),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = CyberpunkColors.TextSecondary
        )
    }
}
