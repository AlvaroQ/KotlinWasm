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
import theme.CyberpunkColors

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
            .background(CyberpunkColors.DarkSurface)
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
                    .background(CyberpunkColors.DarkCard)
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                CyberpunkColors.NeonCyan.copy(alpha = 0.5f),
                                CyberpunkColors.NeonMagenta.copy(alpha = 0.3f)
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
                    .clip(RoundedCornerShape(16.dp))
                    .background(CyberpunkColors.DarkCard)
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                CyberpunkColors.NeonCyan.copy(alpha = 0.5f),
                                CyberpunkColors.NeonMagenta.copy(alpha = 0.3f)
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

        // Highlights row
        if (useColumnLayout) {
            // Column layout for stats
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 500.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                ) {
                    StatCard(
                        modifier = Modifier.weight(1f),
                        value = "+12",
                        label = "YEARS",
                        sublabel = "EXPERIENCE",
                        color = CyberpunkColors.NeonGreen
                    )
                    StatCard(
                        modifier = Modifier.weight(1f),
                        value = "+7",
                        label = "YEARS",
                        sublabel = "LEADING B-FY",
                        color = CyberpunkColors.NeonCyan
                    )
                }
                StatCard(
                    modifier = Modifier.fillMaxWidth(),
                    value = "4",
                    label = "PLATFORMS",
                    sublabel = "ANDROID iOS WEB DESKTOP",
                    color = CyberpunkColors.NeonMagenta
                )
            }
        } else {
            // Row layout for large screens - cards con weight para adaptarse
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "+12",
                    label = "YEARS",
                    sublabel = "EXPERIENCE",
                    color = CyberpunkColors.NeonGreen
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "+7",
                    label = "YEARS",
                    sublabel = "LEADING B-FY",
                    color = CyberpunkColors.NeonCyan
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    value = "4",
                    label = "PLATFORMS",
                    sublabel = "ANDROID iOS WEB DESKTOP",
                    color = CyberpunkColors.NeonMagenta
                )
            }
        }
    }
}

@Composable
private fun PresentationBioContent(isMobile: Boolean = false) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 12.dp else 16.dp),
        horizontalAlignment = if (isMobile) Alignment.CenterHorizontally else Alignment.Start
    ) {
                Text(
                    text = "HI, I'M ALVARO",
                    style = (if (isMobile) MaterialTheme.typography.h4 else MaterialTheme.typography.h3).copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = if (isMobile) 2.sp else 3.sp
                    ),
                    color = CyberpunkColors.NeonCyan,
                    textAlign = if (isMobile) TextAlign.Center else TextAlign.Start
                )

                Text(
                    text = "Senior Mobile Developer & AI Specialist",
                    style = if (isMobile) MaterialTheme.typography.h6 else MaterialTheme.typography.h5,
                    color = CyberpunkColors.TextPrimary,
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
                        color = CyberpunkColors.TextPrimary,
                        icon = Icons.Filled.Build
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Paragraph 1: Kotlin specialist intro
                Text(
                    text = buildAnnotatedString {
                        append("I'm a ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Kotlin specialist")
                        }
                        append(" with over 12 years building ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("mobile applications")
                        }
                        append(". Expert in ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Kotlin Multiplatform (KMP)")
                        }
                        append(" since its first alpha release. In fact, ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("this very site is built with KMP using Kotlin WASM")
                        }
                        append(".")
                    },
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkColors.TextSecondary
                )

                // Paragraph 2: UI & Architecture
                Text(
                    text = buildAnnotatedString {
                        append("I develop modern UIs with ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Jetpack Compose")
                        }
                        append(" and follow clean architecture patterns like ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("MVVM")
                        }
                        append(" and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Clean Architecture")
                        }
                        append(". For dependency injection I prefer ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Hilt")
                        }
                        append(" but also work with ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Koin")
                        }
                        append(" and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Dagger")
                        }
                        append(".")
                    },
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkColors.TextSecondary
                )

                // Paragraph 3: Security focus
                Text(
                    text = buildAnnotatedString {
                        append("I focus on building ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("secure applications")
                        }
                        append(", using techniques like ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("penetration testing")
                        }
                        append(" and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("traffic sniffing")
                        }
                        append(" to debug and improve my developments. I've contributed to major projects at ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Santander Bank")
                        }
                        append(" (UK, Germany, Spain) including biometric authentication, face recognition, and NFC payments.")
                    },
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkColors.TextSecondary
                )

                // Paragraph 4: Workflow & Tools
                Text(
                    text = buildAnnotatedString {
                        append("I work with ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Git")
                        }
                        append(", ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Git Flow")
                        }
                        append(", and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Scrum/Agile")
                        }
                        append(" methodologies. I love ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Firebase")
                        }
                        append(" (")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Push Notifications")
                        }
                        append(", ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Remote Config")
                        }
                        append(", ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Crashlytics")
                        }
                        append(") and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Vercel")
                        }
                        append(" for deployments.")
                    },
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkColors.TextSecondary
                )

                // Paragraph 5: AI focus
                Text(
                    text = buildAnnotatedString {
                        append("My latest focus: ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("AI Agent orchestration")
                        }
                        append(" with ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Claude")
                        }
                        append(", ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("LangChain")
                        }
                        append(", and ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("MCP protocols")
                        }
                        append(".")
                    },
                    style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
                    color = CyberpunkColors.TextSecondary
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

    Box(
        modifier = Modifier
            .then(if (isMobile) Modifier.fillMaxWidth() else Modifier)
            .drawBehind {
                // Rectangular glow effect
                drawRoundRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            CyberpunkColors.NeonCyan.copy(alpha = glowAlpha * 0.4f),
                            CyberpunkColors.NeonMagenta.copy(alpha = glowAlpha * 0.2f),
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

    Box(
        modifier = Modifier
            .drawBehind {
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            CyberpunkColors.NeonMagenta.copy(alpha = 0.2f),
                            CyberpunkColors.NeonCyan.copy(alpha = 0.2f),
                            CyberpunkColors.NeonMagenta.copy(alpha = 0.2f)
                        )
                    ),
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(12.dp))
            .background(CyberpunkColors.DarkCard)
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        CyberpunkColors.NeonMagenta.copy(alpha = borderAlpha),
                        CyberpunkColors.NeonCyan.copy(alpha = borderAlpha),
                        CyberpunkColors.NeonMagenta.copy(alpha = borderAlpha)
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
                    color = CyberpunkColors.TextPrimary,
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
                        color = CyberpunkColors.NeonMagenta
                    )
                    Text(
                        text = "THIS SITE IS 100% BUILT WITH",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        ),
                        color = CyberpunkColors.TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(if (isMobile) 4.dp else 8.dp))

            Text(
                text = "KOTLIN MULTIPLATFORM WASM",
                style = (if (isMobile) MaterialTheme.typography.h6 else MaterialTheme.typography.h4).copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = if (isMobile) 1.sp else 4.sp
                ),
                color = CyberpunkColors.NeonCyan,
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
                        TechBadge("Compose", CyberpunkColors.NeonGreen)
                        TechBadge("Kotlin", CyberpunkColors.NeonMagenta)
                    }
                    TechBadge("WebAssembly", CyberpunkColors.NeonCyan)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TechBadge("Compose", CyberpunkColors.NeonGreen)
                    TechBadge("Kotlin", CyberpunkColors.NeonMagenta)
                    TechBadge("WebAssembly", CyberpunkColors.NeonCyan)
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
private fun SocialLinkButton(text: String, url: String, color: Color, icon: ImageVector? = null) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.15f))
            .border(1.dp, color.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (icon != null) {
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
    color: Color
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
            .clip(RoundedCornerShape(12.dp))
            .background(CyberpunkColors.DarkCard)
            .border(
                width = 1.dp,
                color = color.copy(alpha = glowAlpha),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
            color = color
        )

        Text(
            text = label,
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            ),
            color = CyberpunkColors.TextPrimary
        )

        Text(
            text = sublabel,
            style = MaterialTheme.typography.caption.copy(
                letterSpacing = 1.sp,
                fontSize = 12.sp
            ),
            color = CyberpunkColors.TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}
