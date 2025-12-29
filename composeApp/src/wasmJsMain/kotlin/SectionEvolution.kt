import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.SectionTitle
import data.Breakpoints
import data.CareerData
import data.CareerNode
import data.resolve
import theme.CyberpunkThemeColors
import i18n.Strings
import org.jetbrains.compose.resources.painterResource

@Composable
fun SectionEvolution() {
    val careerTimeline = CareerData.timeline
    val strings = Strings.get()

    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < Breakpoints.MOBILE
    val isTablet = screenWidth in Breakpoints.MOBILE..Breakpoints.TABLET
    val showConnectionLines = screenWidth > Breakpoints.DESKTOP

    // Get colors before drawBehind (non-composable context)
    val backgroundColor = CyberpunkThemeColors.background
    val gridLinesColor = CyberpunkThemeColors.gridLines

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .drawBehind {
                val lineColor = gridLinesColor.copy(alpha = 0.3f)
                for (i in 0..10) {
                    val y = size.height * (i / 10f)
                    drawLine(lineColor, Offset(0f, y), Offset(size.width, y), strokeWidth = 1f)
                }
            }
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = strings.careerTimeline, color = CyberpunkThemeColors.neonCyan)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = strings.careerSubtitle,
            style = MaterialTheme.typography.body1,
            color = CyberpunkThemeColors.textSecondary,
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
                            colorStartTheme = node.themeColor,
                            colorEndTheme = careerTimeline[index + 1].themeColor
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
                            colorStartTheme = node.themeColor,
                            colorEndTheme = careerTimeline[4].themeColor
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
                    .background(CyberpunkThemeColors.card)
                    .border(
                        1.dp,
                        Brush.horizontalGradient(
                            listOf(
                                CyberpunkThemeColors.neonCyan.copy(alpha = 0.5f),
                                CyberpunkThemeColors.neonMagenta.copy(alpha = 0.5f)
                            )
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    ExperienceStat(value = "12+", label = "Years in Tech", color = CyberpunkThemeColors.neonGreen)
                    ExperienceStat(value = "7+", label = "Years at B-FY", color = CyberpunkThemeColors.neonCyan)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    ExperienceStat(value = "4+", label = "Companies", color = CyberpunkThemeColors.neonMagenta)
                    ExperienceStat(value = "5+", label = "Platforms", color = CyberpunkThemeColors.neonPurple)
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(CyberpunkThemeColors.card)
                    .border(
                        1.dp,
                        Brush.horizontalGradient(
                            listOf(
                                CyberpunkThemeColors.neonCyan.copy(alpha = 0.5f),
                                CyberpunkThemeColors.neonMagenta.copy(alpha = 0.5f)
                            )
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(32.dp),
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                ExperienceStat(value = "12+", label = "Years in Tech", color = CyberpunkThemeColors.neonGreen)
                ExperienceStat(value = "7+", label = "Years at B-FY", color = CyberpunkThemeColors.neonCyan)
                ExperienceStat(value = "4+", label = "Companies", color = CyberpunkThemeColors.neonMagenta)
                ExperienceStat(value = "5+", label = "Platforms", color = CyberpunkThemeColors.neonPurple)
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
            color = CyberpunkThemeColors.textSecondary
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
    // Resolve theme-aware color
    val nodeColor = node.themeColor.resolve()

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
        isTablet -> 180.dp
        else -> 220.dp
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
                    .background(nodeColor.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = node.type,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 9.sp,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = nodeColor
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
            color = nodeColor
        )

        Spacer(modifier = Modifier.height(if (isMobile) 6.dp else 12.dp))

        // Icon circle or logo
        val circleSize = if (isMobile) 45.dp else if (isPrimary) 80.dp else 70.dp
        Box(
            modifier = Modifier
                .size(circleSize)
                .drawBehind {
                    drawCircle(
                        color = nodeColor.copy(alpha = glowAlpha),
                        radius = size.minDimension / 2 + (if (isMobile) 8f else 15f)
                    )
                }
                .clip(CircleShape)
                .background(
                    if (node.logoRes != null) {
                        Brush.radialGradient(
                            colors = listOf(
                                CyberpunkThemeColors.logoBackground,
                                CyberpunkThemeColors.logoBackground
                            )
                        )
                    } else {
                        Brush.radialGradient(
                            colors = listOf(
                                nodeColor.copy(alpha = 0.9f),
                                nodeColor.copy(alpha = 0.4f)
                            )
                        )
                    }
                )
                .border(if (isMobile) 1.dp else 2.dp, nodeColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (node.logoRes != null) {
                Image(
                    painter = painterResource(node.logoRes),
                    contentDescription = node.company,
                    modifier = Modifier
                        .size(circleSize * 0.7f)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text(
                    text = node.icon,
                    style = (if (isMobile) MaterialTheme.typography.body2 else MaterialTheme.typography.h5).copy(fontWeight = FontWeight.Bold),
                    color = CyberpunkThemeColors.background
                )
            }
        }

        Spacer(modifier = Modifier.height(if (isMobile) 8.dp else 16.dp))

        // Company name
        Text(
            text = node.company,
            style = (if (isMobile) MaterialTheme.typography.caption else MaterialTheme.typography.h6).copy(fontWeight = FontWeight.Bold),
            color = nodeColor,
            textAlign = TextAlign.Center,
            maxLines = if (isMobile) 2 else 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Role - shown on all sizes
        Text(
            text = node.role,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Medium,
                fontSize = when {
                    isMobile -> 8.sp
                    isTablet -> 12.sp
                    else -> 14.sp
                }
            ),
            color = CyberpunkThemeColors.textPrimary,
            textAlign = TextAlign.Center,
            maxLines = 2
        )

        // Description and Skills - only on tablet and desktop
        if (!isMobile) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = node.description,
                style = MaterialTheme.typography.caption,
                color = CyberpunkThemeColors.textSecondary,
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
                    SkillChip(skill = skill, color = nodeColor)
                }
            }
            if (node.skills.size > 2) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    node.skills.drop(2).forEach { skill ->
                        SkillChip(skill = skill, color = nodeColor)
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
private fun ConnectionLine(colorStartTheme: data.ThemeColor, colorEndTheme: data.ThemeColor) {
    val colorStart = colorStartTheme.resolve()
    val colorEnd = colorEndTheme.resolve()

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
