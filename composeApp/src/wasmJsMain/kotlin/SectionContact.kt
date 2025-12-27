import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.SectionTitle
import components.TerminalText
import theme.CyberpunkColors

@Composable
fun SectionContact() {
    val screenWidth = LocalScreenWidth.current
    val isMobile = screenWidth < 900

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkColors.DarkBackground)
            .padding(vertical = if (isMobile) 40.dp else 80.dp, horizontal = if (isMobile) 20.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = "CONNECT", color = CyberpunkColors.NeonGreen)
        Spacer(modifier = Modifier.height(if (isMobile) 30.dp else 60.dp))

        TerminalText(
            lines = listOf(
                "$ whoami",
                "alvaro_quintana",
                "",
                "$ cat contact.txt",
                "Email: alvaroquintanapalacios@gmail.com",
                "Location: Madrid, Spain (Remote)",
                "",
                "$ cat links.txt",
                "LinkedIn: linkedin.com/in/alvaroquintana",
                "GitHub: github.com/alvaroquintana",
                "",
                "$ echo Ready to build something amazing?"
            ),
            modifier = if (isMobile) Modifier.fillMaxWidth() else Modifier.width(600.dp)
        )

        Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))

        Text(
            text = "Available for AI Agent Development & Mobile Projects",
            style = if (isMobile) MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    else MaterialTheme.typography.h6,
            color = CyberpunkColors.NeonCyan,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}
