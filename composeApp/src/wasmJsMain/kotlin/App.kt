import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import org.w3c.dom.events.Event
import theme.CyberpunkTheme
import theme.CyberpunkColors

// Screen size breakpoints
val LocalScreenWidth = compositionLocalOf { 1920 }

@Composable
fun App() {
    CyberpunkTheme {
        // Usar el tamaño de ventana del navegador directamente
        var screenWidth by remember { mutableStateOf(getWindowWidth()) }

        // Escuchar cambios de tamaño de ventana
        DisposableEffect(Unit) {
            val resizeListener: (Event) -> Unit = {
                screenWidth = getWindowWidth()
            }
            window.addEventListener("resize", resizeListener)
            window.addEventListener("orientationchange", resizeListener)

            onDispose {
                window.removeEventListener("resize", resizeListener)
                window.removeEventListener("orientationchange", resizeListener)
            }
        }

        CompositionLocalProvider(LocalScreenWidth provides screenWidth) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(CyberpunkColors.DarkBackground)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionHeader()
                SectionPresentation()
                SectionEvolution()
                SectionAIProjects()
                SectionSkills()
                SectionContact()
            }
        }
    }
}

// Obtener el ancho de ventana del navegador
private fun getWindowWidth(): Int {
    return window.innerWidth
}
