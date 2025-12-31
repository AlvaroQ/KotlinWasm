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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import components.SectionTitle
import data.Breakpoints
import data.ContactData
import kotlinx.browser.window
import kotlinx.coroutines.delay
import theme.CyberpunkThemeColors
import i18n.Strings
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.focusable
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent

@Composable
fun SectionContact() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < Breakpoints.MOBILE
    val strings = Strings.get()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkThemeColors.background)
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = strings.connect, color = CyberpunkThemeColors.neonGreen)
        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        // Terminal-style contact info with clickable links
        Column(
            modifier = Modifier
                .then(if (isMobile) Modifier.fillMaxWidth() else Modifier.width(600.dp))
                .clip(RoundedCornerShape(8.dp))
                .background(CyberpunkThemeColors.card)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TerminalLine("$ whoami")
            TerminalLine("alvaro_quintana", isOutput = true)
            TerminalLine("")
            TerminalLine("$ cat contact.txt")
            TerminalEmailLine(
                prefix = "Email: ",
                email = ContactData.EMAIL,
                color = CyberpunkThemeColors.neonCyan
            )
            TerminalLine("Location: ${ContactData.LOCATION}", isOutput = true)
            TerminalLine("")
            TerminalLine("$ cat links.txt")
            TerminalLinkLine(
                prefix = "LinkedIn: ",
                text = ContactData.LINKEDIN_DISPLAY,
                url = ContactData.LINKEDIN_URL,
                color = CyberpunkThemeColors.neonMagenta
            )
            TerminalLinkLine(
                prefix = "GitHub: ",
                text = ContactData.GITHUB_DISPLAY,
                url = ContactData.GITHUB_URL,
                color = CyberpunkThemeColors.neonGreen
            )
            TerminalLine("")
            TerminalLine("$ echo Ready to build something amazing?")
        }

    }
}

@Composable
private fun TerminalLine(text: String, isOutput: Boolean = false) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = if (isOutput) CyberpunkThemeColors.textPrimary else CyberpunkThemeColors.neonGreen
    )
}

@Composable
private fun TerminalEmailLine(
    prefix: String,
    email: String,
    color: Color
) {
    val strings = Strings.get()
    val interactionSource = remember { MutableInteractionSource() }
    val copyInteractionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isCopyHovered by copyInteractionSource.collectIsHoveredAsState()
    var copied by remember { mutableStateOf(false) }

    LaunchedEffect(copied) {
        if (copied) {
            delay(2000)
            copied = false
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = prefix,
            style = MaterialTheme.typography.body2,
            color = CyberpunkThemeColors.textPrimary
        )
        Text(
            text = email,
            style = MaterialTheme.typography.body2.copy(
                textDecoration = if (isHovered) TextDecoration.Underline else TextDecoration.None
            ),
            color = if (isHovered) color else color.copy(alpha = 0.8f),
            modifier = Modifier
                .clickable { openUrl("mailto:$email") }
                .hoverable(interactionSource)
                .pointerHoverIcon(PointerIcon.Hand)
        )
        Spacer(modifier = Modifier.width(12.dp))
        // Copy button
        Text(
            text = if (copied) "OK" else "Copy",
            style = MaterialTheme.typography.caption,
            color = if (copied) CyberpunkThemeColors.neonGreen else color,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(
                    if (copied) CyberpunkThemeColors.neonGreen.copy(alpha = 0.2f)
                    else if (isCopyHovered) color.copy(alpha = 0.2f)
                    else color.copy(alpha = 0.1f)
                )
                .border(
                    width = 1.dp,
                    color = if (copied) CyberpunkThemeColors.neonGreen.copy(alpha = 0.5f)
                            else color.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    copyToClipboard(email)
                    copied = true
                }
                .hoverable(copyInteractionSource)
                .pointerHoverIcon(PointerIcon.Hand)
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .semantics {
                    role = Role.Button
                    contentDescription = if (copied) strings.a11yEmailCopied else strings.a11yCopyEmail
                }
        )
    }
}

@Composable
private fun TerminalLinkLine(
    prefix: String,
    text: String,
    url: String,
    color: Color
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row {
        Text(
            text = prefix,
            style = MaterialTheme.typography.body2,
            color = CyberpunkThemeColors.textPrimary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(
                textDecoration = if (isHovered) TextDecoration.Underline else TextDecoration.None
            ),
            color = if (isHovered) color else color.copy(alpha = 0.8f),
            modifier = Modifier
                .clickable { openUrl(url) }
                .hoverable(interactionSource)
                .pointerHoverIcon(PointerIcon.Hand)
        )
    }
}

private fun openUrl(url: String) {
    window.open(url, "_blank")
}

private fun copyToClipboard(text: String) {
    js("navigator.clipboard.writeText(text)")
}
