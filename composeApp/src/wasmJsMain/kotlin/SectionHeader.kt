@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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
import components.FloatingOrb
import components.GlitchText
import components.TypewriterText
import theme.CyberpunkColors
import kotlin.random.Random

@Composable
fun SectionHeader() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < 900

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isMobile) 400.dp else 500.dp)
            .background(CyberpunkColors.DarkBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = if (isMobile) 20.dp else 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "[ SYSTEM INITIALIZED ]",
                style = MaterialTheme.typography.caption,
                color = CyberpunkColors.NeonGreen
            )
            Spacer(modifier = Modifier.height(if (isMobile) 16.dp else 24.dp))
            GlitchText(
                text = "ALVARO QUINTANA",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = if (isMobile) 32.sp else 64.sp,
                    fontWeight = FontWeight.Bold
                ),
                primaryColor = CyberpunkColors.TextPrimary
            )
            Spacer(modifier = Modifier.height(if (isMobile) 16.dp else 24.dp))
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "> ",
                    style = if (isMobile) MaterialTheme.typography.h5 else MaterialTheme.typography.h3,
                    color = CyberpunkColors.NeonCyan
                )
                TypewriterText(
                    text = "MOBILE & AI SPECIALIST",
                    style = (if (isMobile) MaterialTheme.typography.h5 else MaterialTheme.typography.h3).copy(fontWeight = FontWeight.Bold),
                    color = CyberpunkColors.NeonCyan,
                    typingSpeed = 80L
                )
            }
            Spacer(modifier = Modifier.height(if (isMobile) 20.dp else 32.dp))
            Text(
                text = "Android | iOS | KMP | AI Agent Orchestration",
                style = if (isMobile) MaterialTheme.typography.body1 else MaterialTheme.typography.h5,
                color = CyberpunkColors.TextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Built with ",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = if (isMobile) 11.sp else 13.sp
                    ),
                    color = CyberpunkColors.NeonMagenta.copy(alpha = 0.8f)
                )
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "love",
                    tint = CyberpunkColors.NeonMagenta,
                    modifier = Modifier.size(if (isMobile) 14.dp else 16.dp)
                )
                Text(
                    text = " using Kotlin",
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = if (isMobile) 11.sp else 13.sp
                    ),
                    color = CyberpunkColors.NeonMagenta.copy(alpha = 0.8f)
                )
            }
        }
    }
}
