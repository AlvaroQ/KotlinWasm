@file:OptIn(ExperimentalResourceApi::class, ExperimentalResourceApi::class)

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import personalpage.composeapp.generated.resources.Res
import personalpage.composeapp.generated.resources.business_low
import personalpage.composeapp.generated.resources.dancing_script_bold
import personalpage.composeapp.generated.resources.roboto_serif_regular


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    
    MaterialTheme(
        typography = Typography(
            h3 = TextStyle(
                fontFamily = FontFamily(Font(Res.font.dancing_script_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
            ),
            body1 = TextStyle(
                fontFamily = FontFamily(Font(Res.font.roboto_serif_regular)),
                fontWeight = FontWeight.Light,
                fontSize = 20.sp
            )
        )
    ) {
        val screenSize = remember { mutableStateOf(Pair(-1, -1)) }
        val horizontalMarginDescription = if(screenSize.value.first > 1200f) { 240.dp } else { 80.dp }
        updateScreenMeasure(screenSize)

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            SelectionContainer {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    SectionHeader()
                    SectionResumen(screenSize, horizontalMarginDescription)
                    SectionCompanies()
                    SectionPortfolio(screenSize)
                }
            }
        }
//    }
}

@Composable
fun updateScreenMeasure(screenSize: MutableState<Pair<Int, Int>>) {
    Layout(
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
//                    Text("Screen size: ${screenSize.value.first}x${screenSize.value.second}px", modifier = Modifier.align(Alignment.Center))
            }
        },
        measurePolicy = { measurables, constraints ->
            // Use the max width and height from the constraints
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            screenSize.value = Pair(width, height)
            println("Width: $width, height: $height")

            // Measure and place children composables
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

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