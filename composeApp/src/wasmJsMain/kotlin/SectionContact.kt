import androidx.compose.foundation.background
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
import theme.CyberpunkThemeColors
import i18n.Strings

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
            TerminalLinkLine(
                prefix = "Email: ",
                text = ContactData.EMAIL,
                url = "mailto:${ContactData.EMAIL}",
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

        Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))

        Text(
            text = strings.availableFor,
            style = if (isMobile) MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    else MaterialTheme.typography.h6,
            color = CyberpunkThemeColors.neonCyan,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
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
                .hoverable(interactionSource)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable { openUrl(url) }
        )
    }
}

private fun openUrl(url: String) {
    window.open(url, "_blank")
}
