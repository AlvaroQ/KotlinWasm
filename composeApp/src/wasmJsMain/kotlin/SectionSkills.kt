import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import components.NeonCard
import components.NeonSkillBar
import components.SectionTitle
import data.Breakpoints
import data.SkillsData
import data.resolve
import theme.CyberpunkThemeColors
import i18n.Strings

@Composable
fun SectionSkills() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < Breakpoints.MOBILE
    val useColumnLayout = screenWidth < Breakpoints.DESKTOP
    val strings = Strings.get()

    val categories = SkillsData.categories

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkThemeColors.surface)
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = strings.skillsMatrix, color = CyberpunkThemeColors.neonMagenta)
        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        if (useColumnLayout) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 700.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                categories.forEach { category ->
                    val categoryColor = category.themeColor.resolve()
                    SkillCategoryCard(
                        title = category.title,
                        color = categoryColor,
                        skills = category.skills.map { it.name to it.level },
                        useFullWidth = true
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                categories.forEach { category ->
                    val categoryColor = category.themeColor.resolve()
                    SkillCategoryCard(
                        modifier = Modifier.weight(1f),
                        title = category.title,
                        color = categoryColor,
                        skills = category.skills.map { it.name to it.level }
                    )
                }
            }
        }
    }
}

@Composable
private fun SkillCategoryCard(
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
