@file:OptIn(ExperimentalComposeUiApi::class)

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
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.FloatingOrb
import components.GlitchText
import components.TypewriterText
import theme.CyberpunkThemeColors
import theme.LocalThemeMode
import theme.ThemeMode
import i18n.Language
import i18n.LocalLanguage
import i18n.Strings
import kotlin.random.Random
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.focusable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent

@Composable
fun ThemeToggle() {
    val themeMode = LocalThemeMode.current
    val toggleTheme = LocalThemeToggle.current
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val strings = Strings.get()

    val isDark = themeMode == ThemeMode.DARK
    val borderColor = if (isDark) CyberpunkThemeColors.neonCyan else CyberpunkThemeColors.neonMagenta
    val a11yDescription = if (isDark) strings.a11yThemeToggleLight else strings.a11yThemeToggleDark

    Box(
        modifier = Modifier
            .semantics {
                role = Role.Button
                contentDescription = a11yDescription
            }
            .focusable()
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Enter || keyEvent.key == Key.Spacebar) {
                    toggleTheme()
                    true
                } else false
            }
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable { toggleTheme() }
            .clip(RoundedCornerShape(8.dp))
            .background(CyberpunkThemeColors.card.copy(alpha = if (isHovered) 0.9f else 0.7f))
            .border(1.dp, borderColor.copy(alpha = if (isHovered) 1f else 0.6f), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = if (isDark) "[ LIGHT ]" else "[ DARK ]",
            style = MaterialTheme.typography.caption,
            color = borderColor
        )
    }
}

@Composable
fun LanguageToggle() {
    val themeMode = LocalThemeMode.current
    val language = LocalLanguage.current
    val toggleLanguage = LocalLanguageToggle.current
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val strings = Strings.get()

    val isDark = themeMode == ThemeMode.DARK
    val borderColor = if (isDark) CyberpunkThemeColors.neonCyan else CyberpunkThemeColors.neonMagenta
    val a11yDescription = when (language) {
        Language.ES -> strings.a11yLanguageToggleEn
        Language.EN -> strings.a11yLanguageToggleEs
    }

    Box(
        modifier = Modifier
            .semantics {
                role = Role.Button
                contentDescription = a11yDescription
            }
            .focusable()
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Enter || keyEvent.key == Key.Spacebar) {
                    toggleLanguage()
                    true
                } else false
            }
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable { toggleLanguage() }
            .clip(RoundedCornerShape(8.dp))
            .background(CyberpunkThemeColors.card.copy(alpha = if (isHovered) 0.9f else 0.7f))
            .border(1.dp, borderColor.copy(alpha = if (isHovered) 1f else 0.6f), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = when (language) {
                Language.ES -> "[ EN ]"
                Language.EN -> "[ ES ]"
            },
            style = MaterialTheme.typography.caption,
            color = borderColor
        )
    }
}

@Composable
fun SectionHeader() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < 900
    val strings = Strings.get()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isMobile) 400.dp else 500.dp)
            .background(CyberpunkThemeColors.background)
    ) {
        // Language toggle in top left corner
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            LanguageToggle()
        }

        // Theme toggle in top right corner
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            ThemeToggle()
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = if (isMobile) 20.dp else 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = strings.systemInitialized,
                style = MaterialTheme.typography.caption,
                color = CyberpunkThemeColors.neonGreen
            )
            Spacer(modifier = Modifier.height(if (isMobile) 16.dp else 24.dp))
            GlitchText(
                text = strings.name,
                style = MaterialTheme.typography.h1.copy(
                    fontSize = if (isMobile) 32.sp else 64.sp,
                    fontWeight = FontWeight.Bold
                ),
                primaryColor = CyberpunkThemeColors.textPrimary
            )
            Spacer(modifier = Modifier.height(if (isMobile) 16.dp else 24.dp))
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "> ",
                    style = if (isMobile) MaterialTheme.typography.h5 else MaterialTheme.typography.h3,
                    color = CyberpunkThemeColors.neonCyan
                )
                TypewriterText(
                    text = strings.subtitle,
                    style = (if (isMobile) MaterialTheme.typography.h5 else MaterialTheme.typography.h3).copy(fontWeight = FontWeight.Bold),
                    color = CyberpunkThemeColors.neonCyan,
                    typingSpeed = 80L
                )
            }
            Spacer(modifier = Modifier.height(if (isMobile) 20.dp else 32.dp))
            Text(
                text = strings.platforms,
                style = if (isMobile) MaterialTheme.typography.body1 else MaterialTheme.typography.h5,
                color = CyberpunkThemeColors.textSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = strings.builtWith,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = if (isMobile) 11.sp else 13.sp
                    ),
                    color = CyberpunkThemeColors.neonMagenta.copy(alpha = 0.8f)
                )
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "love",
                    tint = CyberpunkThemeColors.neonMagenta,
                    modifier = Modifier.size(if (isMobile) 14.dp else 16.dp)
                )
                Text(
                    text = strings.usingKotlin,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = if (isMobile) 11.sp else 13.sp
                    ),
                    color = CyberpunkThemeColors.neonMagenta.copy(alpha = 0.8f)
                )
            }
        }
    }
}
