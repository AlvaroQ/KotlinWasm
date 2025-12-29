package components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import i18n.Language
import i18n.LocalLanguage
import i18n.Strings
import theme.CyberpunkThemeColors
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.focusable
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent

enum class Section(val id: String, val labelEs: String, val labelEn: String) {
    HEADER("header", "Inicio", "Home"),
    ABOUT("about", "Sobre Mi", "About"),
    CAREER("career", "Trayectoria", "Career"),
    PROJECTS("projects", "Proyectos", "Projects"),
    SKILLS("skills", "Habilidades", "Skills"),
    CONTACT("contact", "Contacto", "Contact")
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavigationMenu(
    currentSection: Section,
    onSectionClick: (Section) -> Unit,
    isMobile: Boolean,
    modifier: Modifier = Modifier
) {
    val language = LocalLanguage.current

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CyberpunkThemeColors.card.copy(alpha = 0.95f))
            .border(
                1.dp,
                CyberpunkThemeColors.neonCyan.copy(alpha = 0.4f),
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = if (isMobile) 8.dp else 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(if (isMobile) 4.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Section.entries.forEach { section ->
            NavigationItem(
                section = section,
                isActive = section == currentSection,
                onClick = { onSectionClick(section) },
                isMobile = isMobile,
                language = language
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun NavigationItem(
    section: Section,
    isActive: Boolean,
    onClick: () -> Unit,
    isMobile: Boolean,
    language: Language
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val strings = Strings.get()

    val label = when (language) {
        Language.ES -> section.labelEs
        Language.EN -> section.labelEn
    }

    val displayLabel = if (isMobile) label.take(3).uppercase() else label.uppercase()

    val a11yDescription = if (isActive) {
        "$label - ${strings.a11yCurrentSection}"
    } else {
        "${strings.a11yNavigateTo} $label"
    }

    val alpha by animateFloatAsState(
        targetValue = when {
            isActive -> 1f
            isHovered -> 0.8f
            else -> 0.5f
        },
        animationSpec = tween(200)
    )

    Box(
        modifier = Modifier
            .semantics {
                role = Role.Button
                contentDescription = a11yDescription
            }
            .focusable()
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Enter || keyEvent.key == Key.Spacebar) {
                    onClick()
                    true
                } else false
            }
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable { onClick() }
            .then(
                if (isActive) {
                    Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(CyberpunkThemeColors.neonCyan.copy(alpha = 0.15f))
                } else Modifier
            )
            .padding(horizontal = if (isMobile) 6.dp else 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = displayLabel,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                letterSpacing = if (isMobile) 0.5.sp else 1.sp,
                fontSize = if (isMobile) 10.sp else 12.sp
            ),
            color = when {
                isActive -> CyberpunkThemeColors.neonCyan
                else -> CyberpunkThemeColors.textSecondary.copy(alpha = alpha)
            }
        )
    }
}
