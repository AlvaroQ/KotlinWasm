import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import components.SectionTitle
import components.TerminalText
import theme.CyberpunkColors

@Composable
fun SectionContact() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkColors.DarkBackground)
            .padding(vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = "CONNECT", color = CyberpunkColors.NeonGreen)
        Spacer(modifier = Modifier.height(60.dp))
        
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
            modifier = Modifier.width(600.dp)
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Text(
            text = "Available for AI Agent Development & Mobile Projects",
            style = MaterialTheme.typography.h6,
            color = CyberpunkColors.NeonCyan,
            fontWeight = FontWeight.Bold
        )
    }
}
