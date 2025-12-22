import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import theme.CyberpunkTheme
import theme.CyberpunkColors

// Screen size breakpoints
val LocalScreenWidth = compositionLocalOf { 1920 }

@Composable
fun App() {
    CyberpunkTheme {
        val screenSize = remember { mutableStateOf(Pair(1920, 1080)) }
        updateScreenMeasure(screenSize)

        val isMobile = screenSize.value.first < 900
        val isTablet = screenSize.value.first in 900..1200

        CompositionLocalProvider(LocalScreenWidth provides screenSize.value.first) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(CyberpunkColors.DarkBackground)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxHeight()
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
}

@Composable
fun updateScreenMeasure(screenSize: MutableState<Pair<Int, Int>>) {
    Layout(
        content = { Box(modifier = Modifier.fillMaxSize()) { } },
        measurePolicy = { measurables, constraints ->
            val width = constraints.maxWidth
            val height = constraints.maxHeight
            screenSize.value = Pair(width, height)
            val placeables = measurables.map { measurable -> measurable.measure(constraints) }
            layout(width, height) {
                var yPosition = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = 0, y = yPosition)
                    yPosition += placeable.height
                }
            }
        }
    )
}
