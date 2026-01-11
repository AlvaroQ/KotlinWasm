import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Build
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import personalpage.composeapp.generated.resources.Res
import personalpage.composeapp.generated.resources.business_front
import personalpage.composeapp.generated.resources.github_logo
import org.jetbrains.compose.resources.DrawableResource
import theme.CyberpunkThemeColors
import i18n.Strings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import kotlinx.browser.window

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SectionPresentation() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < 900
    // Usar columna en móvil/tablet, horizontal en desktop (>1200px)
    val useColumnLayout = screenWidth < 1200

    val horizontalPadding = when {
        isMobile -> 20.dp
        else -> 40.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkThemeColors.surface)
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = horizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main presentation card
        if (useColumnLayout) {
            // Column layout: vertical stack
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 800.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CyberpunkThemeColors.card)
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                CyberpunkThemeColors.neonCyan.copy(alpha = 0.5f),
                                CyberpunkThemeColors.neonMagenta.copy(alpha = 0.3f)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(if (isMobile) 24.dp else 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Profile image
                ProfileImageWithGlow(isMobile = isMobile)

                // Bio content
                PresentationBioContent(isMobile = isMobile)
            }
        } else {
            // Row layout: horizontal for large screens
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 1100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CyberpunkThemeColors.card)
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                CyberpunkThemeColors.neonCyan.copy(alpha = 0.5f),
                                CyberpunkThemeColors.neonMagenta.copy(alpha = 0.3f)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(48.dp),
                horizontalArrangement = Arrangement.spacedBy(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile image with glow effect
                ProfileImageWithGlow()

                // Bio content
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PresentationBioContent(isMobile = false)
                }
            }
        }

        Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))

        // KMP WASM Badge
        KMPWasmBadge(isMobile = isMobile)

        Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))

        // Highlights row - always horizontal, 3 cards aligned
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 900.dp)
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(if (isMobile) 12.dp else 24.dp)
        ) {
            StatCard(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                value = "+12",
                label = "YEARS",
                sublabel = "50+ APPS",
                color = CyberpunkThemeColors.neonGreen,
                compact = isMobile
            )
            StatCard(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                value = "4",
                label = "PLATFORMS",
                sublabel = "1M+ USERS",
                color = CyberpunkThemeColors.neonCyan,
                compact = isMobile
            )
            StatCard(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                value = "SDK",
                label = "B-FY",
                sublabel = "15+ ENTERPRISE APPS",
                color = CyberpunkThemeColors.neonMagenta,
                compact = isMobile
            )
        }
    }
}

@Composable
private fun PresentationBioContent(isMobile: Boolean = false) {
    val strings = Strings.get()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 12.dp else 16.dp),
        horizontalAlignment = if (isMobile) Alignment.CenterHorizontally else Alignment.Start
    ) {
                Text(
                    text = strings.hiImAlvaro,
                    style = (if (isMobile) MaterialTheme.typography.h4 else MaterialTheme.typography.h3).copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = if (isMobile) 2.sp else 3.sp
                    ),
                    color = CyberpunkThemeColors.neonCyan,
                    textAlign = if (isMobile) TextAlign.Center else TextAlign.Start
                )

                Text(
                    text = strings.jobTitle,
                    style = if (isMobile) MaterialTheme.typography.h6 else MaterialTheme.typography.h5,
                    color = CyberpunkThemeColors.textPrimary,
                    textAlign = if (isMobile) TextAlign.Center else TextAlign.Start
                )

                // Social Links
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SocialLinkButton(
                        text = "LinkedIn",
                        url = "linkedin.com/in/alvaro-quintana-palacios",
                        color = Color(0xFF0A66C2),
                        icon = Icons.Filled.Person
                    )
                    SocialLinkButton(
                        text = "GitHub",
                        url = "github.com/AlvaroQ",
                        color = CyberpunkThemeColors.textPrimary,
                        imageRes = Res.drawable.github_logo
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Paragraph 1: Kotlin specialist intro
                Text(
                    text = parseStyledText(strings.bioP1, CyberpunkThemeColors.textSecondary, CyberpunkThemeColors.neonCyan),
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkThemeColors.textSecondary
                )

                // Paragraph 2: UI & Architecture
                Text(
                    text = parseStyledText(strings.bioP2, CyberpunkThemeColors.textSecondary, CyberpunkThemeColors.neonCyan),
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkThemeColors.textSecondary
                )

                // Paragraph 3: Security focus
                Text(
                    text = parseStyledText(strings.bioP3, CyberpunkThemeColors.textSecondary, CyberpunkThemeColors.neonCyan),
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkThemeColors.textSecondary
                )

                // Paragraph 4: Workflow & Tools
                Text(
                    text = parseStyledText(strings.bioP4, CyberpunkThemeColors.textSecondary, CyberpunkThemeColors.neonCyan),
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkThemeColors.textSecondary
                )

                // Paragraph 5: AI focus
                Text(
                    text = parseStyledText(strings.bioP5, CyberpunkThemeColors.textSecondary, CyberpunkThemeColors.neonCyan),
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkThemeColors.textSecondary
                )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ProfileImageWithGlow(isMobile: Boolean = false) {
    val infiniteTransition = rememberInfiniteTransition()

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Get colors before drawBehind
    val neonCyan = CyberpunkThemeColors.neonCyan
    val neonMagenta = CyberpunkThemeColors.neonMagenta

    Box(
        modifier = Modifier
            .then(if (isMobile) Modifier.fillMaxWidth() else Modifier)
            .drawBehind {
                // Rectangular glow effect
                drawRoundRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            neonCyan.copy(alpha = glowAlpha * 0.4f),
                            neonMagenta.copy(alpha = glowAlpha * 0.2f),
                            Color.Transparent
                        )
                    ),
                    cornerRadius = CornerRadius(24.dp.toPx())
                )
            }
            .padding(if (isMobile) 8.dp else 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.business_front),
            contentDescription = "Alvaro Quintana",
            modifier = if (isMobile) {
                Modifier
                    .width(180.dp)
                    .height(240.dp)
                    .clip(RoundedCornerShape(12.dp))
            } else {
                Modifier
                    .width(240.dp)
                    .height(320.dp)
                    .clip(RoundedCornerShape(12.dp))
            },
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
    }
}

@Composable
private fun KMPWasmBadge(isMobile: Boolean = false) {
    val infiniteTransition = rememberInfiniteTransition()

    val borderAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val glowRadius by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Get colors before drawBehind
    val neonMagenta = CyberpunkThemeColors.neonMagenta
    val neonCyan = CyberpunkThemeColors.neonCyan

    Box(
        modifier = Modifier
            .drawBehind {
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            neonMagenta.copy(alpha = 0.2f),
                            neonCyan.copy(alpha = 0.2f),
                            neonMagenta.copy(alpha = 0.2f)
                        )
                    ),
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(12.dp))
            .background(CyberpunkThemeColors.card)
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        CyberpunkThemeColors.neonMagenta.copy(alpha = borderAlpha),
                        CyberpunkThemeColors.neonCyan.copy(alpha = borderAlpha),
                        CyberpunkThemeColors.neonMagenta.copy(alpha = borderAlpha)
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = if (isMobile) 16.dp else 32.dp, vertical = if (isMobile) 12.dp else 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isMobile) {
                // Mobile: stacked layout
                Text(
                    text = "// THIS SITE IS 100% BUILT WITH",
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    color = CyberpunkThemeColors.textPrimary,
                    textAlign = TextAlign.Center
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "//",
                        style = MaterialTheme.typography.h5,
                        color = CyberpunkThemeColors.neonMagenta
                    )
                    Text(
                        text = "THIS SITE IS 100% BUILT WITH",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        color = CyberpunkThemeColors.textPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(if (isMobile) 4.dp else 8.dp))

            Text(
                text = "COMPOSE MULTIPLATFORM WASM",
                style = (if (isMobile) MaterialTheme.typography.h6 else MaterialTheme.typography.h4).copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = if (isMobile) 1.sp else 4.sp
                ),
                color = CyberpunkThemeColors.neonCyan,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(if (isMobile) 6.dp else 8.dp))

            if (isMobile) {
                // Mobile: wrap badges
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TechBadge("Compose", CyberpunkThemeColors.neonGreen)
                        TechBadge("Kotlin", CyberpunkThemeColors.neonMagenta)
                    }
                    TechBadge("WebAssembly", CyberpunkThemeColors.neonCyan)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TechBadge("Compose", CyberpunkThemeColors.neonGreen)
                    TechBadge("Kotlin", CyberpunkThemeColors.neonMagenta)
                    TechBadge("WebAssembly", CyberpunkThemeColors.neonCyan)
                }
            }
        }
    }
}

@Composable
private fun TechBadge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.15f))
            .border(1.dp, color.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            color = color
        )
    }
}

@Composable
@OptIn(ExperimentalResourceApi::class)
private fun SocialLinkButton(
    text: String,
    url: String,
    color: Color,
    icon: ImageVector? = null,
    imageRes: DrawableResource? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val fullUrl = if (url.startsWith("http")) url else "https://$url"

    Box(
        modifier = Modifier
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable { window.open(fullUrl, "_blank") }
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = if (isHovered) 0.25f else 0.15f))
            .border(1.dp, color.copy(alpha = if (isHovered) 0.8f else 0.5f), RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (imageRes != null) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = text,
                    modifier = Modifier.size(18.dp)
                )
            } else if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    tint = color,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = color
            )
        }
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    sublabel: String,
    color: Color,
    compact: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition()

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(if (compact) 8.dp else 12.dp))
            .background(CyberpunkThemeColors.card)
            .border(
                width = 1.dp,
                color = color.copy(alpha = glowAlpha),
                shape = RoundedCornerShape(if (compact) 8.dp else 12.dp)
            )
            .padding(if (compact) 12.dp else 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = (if (compact) MaterialTheme.typography.h4 else MaterialTheme.typography.h2)
                .copy(fontWeight = FontWeight.Bold),
            color = color
        )

        Text(
            text = label,
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = if (compact) 1.sp else 2.sp,
                fontSize = if (compact) 10.sp else 14.sp
            ),
            color = CyberpunkThemeColors.textPrimary
        )

        Text(
            text = sublabel,
            style = MaterialTheme.typography.caption.copy(
                letterSpacing = if (compact) 0.5.sp else 1.sp,
                fontSize = if (compact) 9.sp else 12.sp
            ),
            color = CyberpunkThemeColors.textSecondary,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Parses text with **bold** markers and returns an AnnotatedString
 */
@Composable
private fun parseStyledText(text: String, baseColor: Color, boldColor: Color): androidx.compose.ui.text.AnnotatedString {
    return buildAnnotatedString {
        var currentIndex = 0
        val boldPattern = Regex("\\*\\*(.+?)\\*\\*")

        boldPattern.findAll(text).forEach { match ->
            // Add text before the bold part
            if (match.range.first > currentIndex) {
                append(text.substring(currentIndex, match.range.first))
            }
            // Add the bold text
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = boldColor)) {
                append(match.groupValues[1])
            }
            currentIndex = match.range.last + 1
        }
        // Add remaining text
        if (currentIndex < text.length) {
            append(text.substring(currentIndex))
        }
    }
}
