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
    // Usar columna hasta pantallas muy grandes (>1400px)
    val useColumnLayout = screenWidth < 1400

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkColors.DarkSurface)
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = "SKILLS MATRIX", color = CyberpunkColors.NeonMagenta)
        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        if (useColumnLayout) {
            // Column layout - vertical stack
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 700.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SkillCategory(
                    title = "Development",
                    color = CyberpunkColors.NeonGreen,
                    skills = listOf(
                        "Kotlin" to 0.95f,
                        "Jetpack Compose" to 0.90f,
                        "Android SDK" to 0.92f,
                        "iOS / Swift" to 0.70f,
                        "KMP" to 0.88f,
                        "HTML/JS/CSS" to 0.85f
                    ),
                    useFullWidth = true
                )

                SkillCategory(
                    title = "AI / ML Orchestration",
                    color = CyberpunkColors.NeonMagenta,
                    skills = listOf(
                        "Claude / Anthropic" to 0.90f,
                        "Cursor / Copilot" to 0.80f,
                        "LangChain" to 0.85f,
                        "MCP Protocol" to 0.80f,
                        "Prompt Engineering" to 0.88f,
                        "n8n" to 0.85f
                    ),
                    useFullWidth = true
                )

                SkillCategory(
                    title = "Backend / Tools",
                    color = CyberpunkColors.NeonCyan,
                    skills = listOf(
                        "Ktor" to 0.82f,
                        "Firebase" to 0.88f,
                        "Git / CI-CD" to 0.91f,
                        "REST / GraphQL" to 0.92f,
                        "Pen-testing" to 0.89f,
                        "Domotics" to 0.82f
                    ),
                    useFullWidth = true
                )
            }
        } else {
            // Row layout - cards con weight para adaptarse
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SkillCategory(
                    modifier = Modifier.weight(1f),
                    title = "Development",
                    color = CyberpunkColors.NeonGreen,
                    skills = listOf(
                        "Kotlin" to 0.95f,
                        "Jetpack Compose" to 0.90f,
                        "Android SDK" to 0.92f,
                        "iOS / Swift" to 0.70f,
                        "KMP" to 0.88f,
                        "HTML/JS/CSS" to 0.85f
                    )
                )

                SkillCategory(
                    modifier = Modifier.weight(1f),
                    title = "AI / ML Orchestration",
                    color = CyberpunkColors.NeonMagenta,
                    skills = listOf(
                        "Claude / Anthropic" to 0.90f,
                        "Cursor / Copilot" to 0.80f,
                        "LangChain" to 0.85f,
                        "MCP Protocol" to 0.80f,
                        "Prompt Engineering" to 0.88f,
                        "n8n" to 0.85f
                    )
                )

                SkillCategory(
                    modifier = Modifier.weight(1f),
                    title = "Backend / Tools",
                    color = CyberpunkColors.NeonCyan,
                    skills = listOf(
                        "Ktor" to 0.82f,
                        "Firebase" to 0.88f,
                        "Git / CI-CD" to 0.90f,
                        "REST / GraphQL" to 0.92f,
                        "Pen-testing" to 0.85f,
                        "Domotics" to 0.88f
                    )
                )
            }
        }
    }
}

@Composable
private fun SkillCategory(
    modifier: Modifier = Modifier,
    title: String,
    color: Color,
    skills: List<Pair<String, Float>>,
    useFullWidth: Boolean = false
) {
    NeonCard(
        glowColor = color,
        modifier = modifier.then(if (useFullWidth) Modifier.fillMaxWidth() else Modifier)
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
