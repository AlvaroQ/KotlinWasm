import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.SectionTitle
import data.AIProjectsData
import data.Breakpoints
import data.ProjectDetails
import kotlinx.browser.window
import theme.CyberpunkColors
import theme.CyberpunkColorsLight
import theme.CyberpunkThemeColors
import theme.LocalThemeMode
import theme.ThemeMode
import i18n.Strings
import i18n.LocalizedString

@Composable
fun SectionAIProjects() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < Breakpoints.MOBILE
    val useColumnLayout = screenWidth < Breakpoints.WIDE
    val strings = Strings.get()

    val projects = AIProjectsData.projects

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CyberpunkThemeColors.background,
                        CyberpunkThemeColors.background,
                        CyberpunkThemeColors.background
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
                tint = CyberpunkThemeColors.neonMagenta,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = strings.latestAiProjects,
                style = MaterialTheme.typography.h3,
                color = CyberpunkThemeColors.neonMagenta
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = CyberpunkThemeColors.neonMagenta,
                modifier = Modifier.size(32.dp)
            )
        }

        Text(
            text = strings.showcasingProjects,
            style = MaterialTheme.typography.body1,
            color = CyberpunkThemeColors.textSecondary,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        if (useColumnLayout) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (isMobile) 0.dp else 60.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                projects.forEach { project ->
                    AIProjectCard(
                        title = project.title,
                        subtitle = project.subtitle,
                        description = project.description,
                        features = project.features,
                        techStack = project.techStack,
                        accentColor = project.accentColor,
                        githubUrl = project.githubUrl,
                        isLive = project.isLive,
                        isPrivate = project.isPrivate,
                        isFeatured = project.isFeatured,
                        detailedInfo = project.detailedInfo,
                        useFullWidth = true
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                projects.forEach { project ->
                    AIProjectCard(
                        modifier = Modifier.weight(1f),
                        title = project.title,
                        subtitle = project.subtitle,
                        description = project.description,
                        features = project.features,
                        techStack = project.techStack,
                        accentColor = project.accentColor,
                        githubUrl = project.githubUrl,
                        isLive = project.isLive,
                        isPrivate = project.isPrivate,
                        isFeatured = project.isFeatured,
                        detailedInfo = project.detailedInfo
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))
    }
}

/**
 * Maps dark theme accent colors to their light theme equivalents
 */
@Composable
private fun getThemeAwareAccentColor(color: Color): Color {
    val isLightMode = LocalThemeMode.current == ThemeMode.LIGHT
    if (!isLightMode) return color

    return when (color) {
        CyberpunkColors.NeonCyan -> CyberpunkColorsLight.NeonCyan
        CyberpunkColors.NeonGreen -> CyberpunkColorsLight.NeonGreen
        CyberpunkColors.NeonMagenta -> CyberpunkColorsLight.NeonMagenta
        CyberpunkColors.NeonPurple -> CyberpunkColorsLight.NeonPurple
        CyberpunkColors.NeonPink -> CyberpunkColorsLight.NeonPink
        CyberpunkColors.NeonYellow -> CyberpunkColorsLight.NeonYellow
        CyberpunkColors.Orchid -> CyberpunkColorsLight.Orchid
        CyberpunkColors.SantanderBlue -> CyberpunkColorsLight.SantanderBlue
        else -> color
    }
}

@Composable
private fun AIProjectCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: LocalizedString,
    description: LocalizedString,
    features: List<LocalizedString>,
    techStack: List<String>,
    accentColor: Color,
    githubUrl: String? = null,
    isLive: Boolean = false,
    isPrivate: Boolean = false,
    isFeatured: Boolean = false,
    detailedInfo: ProjectDetails? = null,
    useFullWidth: Boolean = false
) {
    val strings = Strings.get()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Use theme-aware color
    val themeAccentColor = getThemeAwareAccentColor(accentColor)

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
                    color = themeAccentColor.copy(alpha = glowAlpha),
                    cornerRadius = CornerRadius(16.dp.toPx()),
                    size = size.copy(
                        width = size.width + 20,
                        height = size.height + 20
                    ),
                    topLeft = Offset(-10f, -10f)
                )
            }
            .clip(RoundedCornerShape(16.dp))
            .background(CyberpunkThemeColors.card)
            .border(
                width = if (isHovered) 2.dp else 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(themeAccentColor, themeAccentColor.copy(alpha = 0.5f))
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
                // Featured badge
                if (isFeatured) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        themeAccentColor.copy(alpha = 0.2f),
                                        themeAccentColor.copy(alpha = 0.1f)
                                    )
                                )
                            )
                            .border(1.dp, themeAccentColor.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = strings.featured,
                            style = MaterialTheme.typography.caption.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = themeAccentColor
                        )
                    }
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    color = themeAccentColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle.get(),
                    style = MaterialTheme.typography.caption,
                    color = CyberpunkThemeColors.textSecondary
                )
            }

            // Badge: LIVE (animated) or AI
            if (isLive) {
                val infiniteTransition = rememberInfiniteTransition()
                val liveGlow by infiniteTransition.animateFloat(
                    initialValue = 0.6f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(800, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(CyberpunkThemeColors.neonGreen.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(CyberpunkThemeColors.neonGreen.copy(alpha = liveGlow))
                    )
                    Text(
                        text = strings.liveBadge,
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                        color = CyberpunkThemeColors.neonGreen
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(themeAccentColor.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "AI",
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                        color = themeAccentColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = description.get(),
            style = MaterialTheme.typography.body2,
            color = CyberpunkThemeColors.textPrimary,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Features grid
        Text(
            text = strings.keyFeatures,
            style = MaterialTheme.typography.caption.copy(letterSpacing = 2.sp),
            color = CyberpunkThemeColors.textSecondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Features layout: 4 in row on desktop, 2 columns on mobile
        val screenWidth = LocalScreenWidth.current
        val useCompactFeatures = screenWidth < Breakpoints.TABLET

        if (useCompactFeatures) {
            // Mobile/Tablet: 2 columns aligned with space between
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    features.take(2).forEach { feature ->
                        FeatureItem(feature, themeAccentColor)
                    }
                }
                Column(horizontalAlignment = Alignment.Start) {
                    features.drop(2).forEach { feature ->
                        FeatureItem(feature, themeAccentColor)
                    }
                }
            }
        } else {
            // Desktop: all 4 in a row spanning full width
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                features.forEach { feature ->
                    FeatureItem(feature, themeAccentColor)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tech stack
        Text(
            text = strings.techStack,
            style = MaterialTheme.typography.caption.copy(letterSpacing = 2.sp),
            color = CyberpunkThemeColors.textSecondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            techStack.forEach { tech ->
                TechChip(tech, themeAccentColor)
                Spacer(modifier = Modifier.width(6.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // GitHub link OR Private badge
            if (githubUrl != null) {
                val linkInteractionSource = remember { MutableInteractionSource() }
                val isLinkHovered by linkInteractionSource.collectIsHoveredAsState()

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isLinkHovered) themeAccentColor.copy(alpha = 0.2f) else themeAccentColor.copy(alpha = 0.1f))
                        .border(
                            width = if (isLinkHovered) 2.dp else 1.dp,
                            color = themeAccentColor.copy(alpha = if (isLinkHovered) 0.6f else 0.3f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .hoverable(linkInteractionSource)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable { openGitHubUrl(githubUrl) }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = strings.viewOnGithub,
                        style = MaterialTheme.typography.caption,
                        color = themeAccentColor
                    )
                }
            }

            // "View Details" button - for all projects with detailed info
            if (detailedInfo != null) {
                val detailsInteractionSource = remember { MutableInteractionSource() }
                val isDetailsHovered by detailsInteractionSource.collectIsHoveredAsState()

                // Pre-compute composable values outside of clickable lambda
                val subtitleText = subtitle.get()
                val longDescriptionText = detailedInfo.longDescription.get()
                val architectureText = detailedInfo.architecture.get()
                val keyHighlightsTexts = detailedInfo.keyHighlights.map { it.get() }
                val accentColorHex = colorToHex(themeAccentColor)

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isDetailsHovered)
                                Brush.horizontalGradient(listOf(themeAccentColor, themeAccentColor.copy(alpha = 0.8f)))
                            else
                                Brush.horizontalGradient(listOf(themeAccentColor.copy(alpha = 0.8f), themeAccentColor.copy(alpha = 0.6f)))
                        )
                        .hoverable(detailsInteractionSource)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable {
                            openProjectModal(
                                buildProjectJson(
                                    title = title,
                                    subtitle = subtitleText,
                                    longDescription = longDescriptionText,
                                    architecture = architectureText,
                                    screenshots = detailedInfo.screenshots,
                                    keyHighlights = keyHighlightsTexts,
                                    techStack = techStack,
                                    videoPlaceholder = detailedInfo.videoPlaceholder,
                                    accentColor = accentColorHex
                                )
                            )
                        }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "> ${strings.viewDetails}",
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
            }

            // "Try it" button - only for live projects
            if (isLive) {
                val tryInteractionSource = remember { MutableInteractionSource() }
                val isTryHovered by tryInteractionSource.collectIsHoveredAsState()

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isTryHovered)
                                Brush.horizontalGradient(listOf(CyberpunkThemeColors.neonCyan, CyberpunkThemeColors.neonMagenta))
                            else
                                Brush.horizontalGradient(listOf(CyberpunkThemeColors.neonCyan.copy(alpha = 0.8f), CyberpunkThemeColors.neonMagenta.copy(alpha = 0.8f)))
                        )
                        .hoverable(tryInteractionSource)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable { openChatWidget() }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "> ${strings.tryIt}",
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Build JSON for project modal
private fun buildProjectJson(
    title: String,
    subtitle: String,
    longDescription: String,
    architecture: String,
    screenshots: List<String>,
    keyHighlights: List<String>,
    techStack: List<String>,
    videoPlaceholder: Boolean,
    accentColor: String
): String {
    val highlightsJson = keyHighlights.joinToString(",") { "\"${escapeJson(it)}\"" }
    val techStackJson = techStack.joinToString(",") { "\"${escapeJson(it)}\"" }
    val screenshotsJson = screenshots.joinToString(",") { "\"${escapeJson(it)}\"" }
    return """{
        "title": "${escapeJson(title)}",
        "subtitle": "${escapeJson(subtitle)}",
        "longDescription": "${escapeJson(longDescription)}",
        "architecture": "${escapeJson(architecture)}",
        "screenshots": [$screenshotsJson],
        "keyHighlights": [$highlightsJson],
        "techStack": [$techStackJson],
        "videoPlaceholder": $videoPlaceholder,
        "accentColor": "$accentColor"
    }"""
}

private fun escapeJson(text: String): String {
    return text
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
        .replace("\r", "\\r")
        .replace("\t", "\\t")
}

// Convert Color to hex string
private fun colorToHex(color: Color): String {
    val r = (color.red * 255).toInt()
    val g = (color.green * 255).toInt()
    val b = (color.blue * 255).toInt()
    return "#${r.toString(16).padStart(2, '0')}${g.toString(16).padStart(2, '0')}${b.toString(16).padStart(2, '0')}"
}

// Open project modal - JS interop
private fun openProjectModal(json: String): Unit = js("window.openProjectModal && window.openProjectModal(json)")

@Composable
private fun FeatureItem(text: LocalizedString, color: Color) {
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
            text = text.get(),
            style = MaterialTheme.typography.body2,
            color = CyberpunkThemeColors.textPrimary
        )
    }
}

@Composable
private fun TechChip(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.2f))
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
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
            color = CyberpunkThemeColors.textSecondary
        )
    }
}

// Open URL function - top-level for WASM compatibility
private fun openGitHubUrl(url: String) {
    window.open(url, "_blank")
}

// Open chat widget - JS interop
private fun openChatWidget(): Unit = js("window.openChatWidget && window.openChatWidget()")
