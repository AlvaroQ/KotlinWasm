import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import components.NeonCard
import components.NeonSkillBar
import components.SectionTitle
import theme.CyberpunkColors

@Composable
fun SectionSkills() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < 900

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkColors.DarkSurface)
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = "SKILLS MATRIX", color = CyberpunkColors.NeonMagenta)
        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        if (isMobile) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SkillCategory(
                    title = "Mobile Development",
                    color = CyberpunkColors.NeonGreen,
                    skills = listOf(
                        "Kotlin" to 0.95f,
                        "Jetpack Compose" to 0.90f,
                        "Android SDK" to 0.92f,
                        "iOS / Swift" to 0.70f,
                        "KMP" to 0.88f
                    ),
                    isMobile = true
                )

                SkillCategory(
                    title = "AI / ML Orchestration",
                    color = CyberpunkColors.NeonMagenta,
                    skills = listOf(
                        "Claude / Anthropic" to 0.90f,
                        "Cursor / Copilot" to 0.80f,
                        "LangChain" to 0.85f,
                        "MCP Protocol" to 0.80f,
                        "Prompt Engineering" to 0.88f
                    ),
                    isMobile = true
                )

                SkillCategory(
                    title = "Backend / Tools",
                    color = CyberpunkColors.NeonCyan,
                    skills = listOf(
                        "Ktor" to 0.82f,
                        "Firebase" to 0.88f,
                        "Git / CI-CD" to 0.90f,
                        "REST / GraphQL" to 0.92f
                    ),
                    isMobile = true
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SkillCategory(
                    title = "Mobile Development",
                    color = CyberpunkColors.NeonGreen,
                    skills = listOf(
                        "Kotlin" to 0.95f,
                        "Jetpack Compose" to 0.90f,
                        "Android SDK" to 0.92f,
                        "iOS / Swift" to 0.70f,
                        "KMP" to 0.88f
                    )
                )

                SkillCategory(
                    title = "AI / ML Orchestration",
                    color = CyberpunkColors.NeonMagenta,
                    skills = listOf(
                        "Claude / Anthropic" to 0.90f,
                        "Cursor / Copilot" to 0.80f,
                        "LangChain" to 0.85f,
                        "MCP Protocol" to 0.80f,
                        "Prompt Engineering" to 0.88f
                    )
                )

                SkillCategory(
                    title = "Backend / Tools",
                    color = CyberpunkColors.NeonCyan,
                    skills = listOf(
                        "Ktor" to 0.82f,
                        "Firebase" to 0.88f,
                        "Git / CI-CD" to 0.90f,
                        "REST / GraphQL" to 0.92f
                    )
                )
            }
        }
    }
}

@Composable
private fun SkillCategory(
    title: String,
    color: Color,
    skills: List<Pair<String, Float>>,
    isMobile: Boolean = false
) {
    NeonCard(
        glowColor = color,
        modifier = if (isMobile) Modifier.fillMaxWidth() else Modifier.width(350.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = color,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        skills.forEachIndexed { index, (skill, level) ->
            NeonSkillBar(
                skill = skill,
                level = level,
                barColor = color,
                animationDelay = index * 200
            )
        }
    }
}
