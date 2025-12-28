import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.SectionTitle
import theme.CyberpunkColors

data class CareerNode(
    val company: String,
    val period: String,
    val role: String,
    val description: String,
    val skills: List<String>,
    val color: Color,
    val icon: String,
    val type: String
)

@Composable
fun SectionEvolution() {
    val careerTimeline = listOf(
        CareerNode(
            company = "TheRanking",
            period = "2013 - 2014",
            role = "Android & iOS Developer",
            description = "Cross-platform development with Titanium. Built startup apps with social integrations.",
            skills = listOf("Titanium", "Java", "REST APIs", "Git"),
            color = CyberpunkColors.NeonCyan,
            icon = "TR",
            type = "STARTUP"
        ),
        CareerNode(
            company = "TalentoMOBILE",
            period = "2015 - 2017",
            role = "Senior Mobile Developer",
            description = "Santander Bank projects: biometric signature, face recognition, voice commands, NFC.",
            skills = listOf("Android", "Biometrics", "voice commands", "Security"),
            color = CyberpunkColors.NeonGreen,
            icon = "TM",
            type = "CONSULTANT"
        ),
        CareerNode(
            company = "Santander UK",
            period = "2017",
            role = "Senior Developer & Android Specialist",
            description = "On-site in Milton Keynes. Retail & Business apps with Kotlin, NFC, OCR, geolocation.",
            skills = listOf("Kotlin", "NFC", "OCR", "MVP"),
            color = Color(0xFF4A90D9), // Santander blue-ish
            icon = "UK",
            type = "CORPORATE"
        ),
        CareerNode(
            company = "B-FY",
            period = "2018 - Present",
            role = "App Development Director",
            description = "Leading multi-platform development: Android, iOS, Desktop (Win/Mac/Linux) with KMP.",
            skills = listOf("KMP", "iOS", "Compose", "Ktor"),
            color = CyberpunkColors.NeonMagenta,
            icon = "BF",
            type = "STARTUP"
        ),
        CareerNode(
            company = "AI Specialist",
            period = "2024 - Present",
            role = "AI Agent Orchestrator",
            description = "Building intelligent systems with LLMs, autonomous agents, and AI-powered workflows.",
            skills = listOf("Claude", "LangChain", "MCP", "RAG"),
            color = Color(0xFFDA70D6), // Orchid - más brillante que NeonPurple
            icon = "AI",
            type = "SPECIALIST"
        )
    )

    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < 900
    val isTablet = screenWidth in 900..1200
    val showConnectionLines = screenWidth > 1400

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkColors.DarkBackground)
            .drawBehind {
                val lineColor = CyberpunkColors.GridLines.copy(alpha = 0.3f)
                for (i in 0..10) {
                    val y = size.height * (i / 10f)
                    drawLine(lineColor, Offset(0f, y), Offset(size.width, y), strokeWidth = 1f)
                }
            }
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = "CAREER TIMELINE", color = CyberpunkColors.NeonCyan)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "From startup developer to enterprise architect to AI specialist",
            style = MaterialTheme.typography.body1,
            color = CyberpunkColors.TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        // Timeline - 2 rows layout for all screen sizes
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (isMobile) 0.dp else 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(if (isMobile) 20.dp else 40.dp)
        ) {
            // Row 1: TheRanking, TalentoMobile, Santander UK
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top
            ) {
                careerTimeline.take(3).forEachIndexed { index, node ->
                    CareerNodeCard(node = node, isPrimary = false, isMobile = isMobile, isTablet = isTablet)
                    // Solo mostrar líneas de conexión en pantallas muy grandes
                    if (index < 2 && showConnectionLines) {
                        ConnectionLine(
                            colorStart = node.color,
                            colorEnd = careerTimeline[index + 1].color
                        )
                    }
                }
            }
            // Row 2: B-FY, AI Specialist
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top
            ) {
                careerTimeline.drop(3).forEachIndexed { index, node ->
                    CareerNodeCard(node = node, isPrimary = node.company == "AI Specialist", isMobile = isMobile, isTablet = isTablet)
                    // Solo mostrar líneas de conexión en pantallas muy grandes
                    if (index < 1 && showConnectionLines) {
                        ConnectionLine(
                            colorStart = node.color,
                            colorEnd = careerTimeline[4].color
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        // Experience summary
        if (isMobile) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(CyberpunkColors.DarkCard)
                    .border(
                        1.dp,
                        Brush.horizontalGradient(
                            listOf(
                                CyberpunkColors.NeonCyan.copy(alpha = 0.5f),
                                CyberpunkColors.NeonMagenta.copy(alpha = 0.5f)
                            )
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    ExperienceStat(value = "12+", label = "Years in Tech", color = CyberpunkColors.NeonGreen)
                    ExperienceStat(value = "7+", label = "Years at B-FY", color = CyberpunkColors.NeonCyan)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    ExperienceStat(value = "4+", label = "Companies", color = CyberpunkColors.NeonMagenta)
                    ExperienceStat(value = "5+", label = "Platforms", color = CyberpunkColors.NeonPurple)
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(CyberpunkColors.DarkCard)
                    .border(
                        1.dp,
                        Brush.horizontalGradient(
                            listOf(
                                CyberpunkColors.NeonCyan.copy(alpha = 0.5f),
                                CyberpunkColors.NeonMagenta.copy(alpha = 0.5f)
                            )
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(32.dp),
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                ExperienceStat(value = "12+", label = "Years in Tech", color = CyberpunkColors.NeonGreen)
                ExperienceStat(value = "7+", label = "Years at B-FY", color = CyberpunkColors.NeonCyan)
                ExperienceStat(value = "4+", label = "Companies", color = CyberpunkColors.NeonMagenta)
                ExperienceStat(value = "5+", label = "Platforms", color = CyberpunkColors.NeonPurple)
            }
        }
    }
}

@Composable
private fun ExperienceStat(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.h4,
            color = color,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = CyberpunkColors.TextSecondary
        )
    }
}

@Composable
private fun CareerNodeCard(
    node: CareerNode,
    isPrimary: Boolean = false,
    isMobile: Boolean = false,
    isTablet: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = if (isPrimary) 0.8f else 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val cardWidth = when {
        isMobile -> 100.dp
        isTablet -> 150.dp
        else -> 200.dp
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(cardWidth)
    ) {
        // Type badge - hide on mobile and tablet to save space
        if (!isMobile && !isTablet) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(node.color.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = node.type,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 9.sp,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = node.color
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Period
        Text(
            text = if (isMobile) node.period.takeLast(4) else node.period, // Just year on mobile
            style = MaterialTheme.typography.caption.copy(
                letterSpacing = if (isMobile) 1.sp else 2.sp,
                fontSize = if (isMobile) 8.sp else 12.sp
            ),
            color = node.color.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(if (isMobile) 6.dp else 12.dp))

        // Icon circle
        Box(
            modifier = Modifier
                .size(if (isMobile) 45.dp else if (isPrimary) 80.dp else 70.dp)
                .drawBehind {
                    drawCircle(
                        color = node.color.copy(alpha = glowAlpha),
                        radius = size.minDimension / 2 + (if (isMobile) 8f else 15f)
                    )
                }
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            node.color.copy(alpha = 0.9f),
                            node.color.copy(alpha = 0.4f)
                        )
                    )
                )
                .border(if (isMobile) 1.dp else 2.dp, node.color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = node.icon,
                style = (if (isMobile) MaterialTheme.typography.body2 else MaterialTheme.typography.h5).copy(fontWeight = FontWeight.Bold),
                color = CyberpunkColors.DarkBackground
            )
        }

        Spacer(modifier = Modifier.height(if (isMobile) 8.dp else 16.dp))

        // Company name
        Text(
            text = node.company,
            style = (if (isMobile) MaterialTheme.typography.caption else MaterialTheme.typography.h6).copy(fontWeight = FontWeight.Bold),
            color = node.color,
            textAlign = TextAlign.Center,
            maxLines = if (isMobile) 2 else 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Role - shown on all sizes
        Text(
            text = node.role,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Medium,
                fontSize = if (isMobile) 8.sp else 14.sp
            ),
            color = CyberpunkColors.TextPrimary,
            textAlign = TextAlign.Center,
            maxLines = if (isMobile) 2 else 1
        )

        // Description and Skills - only on tablet and desktop
        if (!isMobile) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = node.description,
                style = MaterialTheme.typography.caption,
                color = CyberpunkColors.TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Skills
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                node.skills.take(2).forEach { skill ->
                    SkillChip(skill = skill, color = node.color)
                }
            }
            if (node.skills.size > 2) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    node.skills.drop(2).forEach { skill ->
                        SkillChip(skill = skill, color = node.color)
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillChip(skill: String, color: Color) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.15f))
            .border(1.dp, color.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.caption.copy(fontSize = 9.sp),
            color = color
        )
    }
}

@Composable
private fun ConnectionLine(colorStart: Color, colorEnd: Color) {
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .width(60.dp)
            .height(4.dp)
            .offset(y = 80.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorStart.copy(alpha = 0.3f),
                        colorEnd.copy(alpha = 0.8f),
                        colorEnd.copy(alpha = 0.3f)
                    ),
                    startX = progress * 200f - 50f,
                    endX = progress * 200f + 50f
                )
            )
    )
}
