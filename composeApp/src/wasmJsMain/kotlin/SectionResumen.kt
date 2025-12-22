import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import personalpage.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SectionResumen(screenSize: MutableState<Pair<Int, Int>>, horizontalMarginDescription: Dp) {
    Row(
        modifier = Modifier
            .padding(start = horizontalMarginDescription, end = horizontalMarginDescription, top = 30.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(4f),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = buildAnnotatedString {
                    append(stringResource(Res.string.body_one_paragraph_one_one))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_one_two))
                    }
                    append(stringResource(Res.string.body_one_paragraph_one_three))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_one_four))
                    }
                    append(stringResource(Res.string.body_one_paragraph_one_five))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_one_six))
                    }
                    append(stringResource(Res.string.body_one_paragraph_one_seven))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_one_eight))
                    }
                    append(stringResource(Res.string.body_one_paragraph_one_nine))
                },
                style = MaterialTheme.typography.body1
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = buildAnnotatedString {
                    append(stringResource(Res.string.body_one_paragraph_two_one))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_two_two))
                    }
                    append(stringResource(Res.string.body_one_paragraph_two_three))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_two_four))
                    }
                    append(stringResource(Res.string.body_one_paragraph_two_five))
                },
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = buildAnnotatedString {
                    append(stringResource(Res.string.body_one_paragraph_three_one))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_three_two))
                    }
                    append(stringResource(Res.string.body_one_paragraph_three_three))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_three_four))
                    }
                    append(stringResource(Res.string.body_one_paragraph_three_five))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_three_six))
                    }
                    append(stringResource(Res.string.body_one_paragraph_three_seven))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_one_paragraph_three_eight))
                    }
                    append(stringResource(Res.string.body_one_paragraph_three_nine))
                },
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )

        }


        if(screenSize.value.first > 1200f) {
            Image(
                painter = painterResource(Res.drawable.business_front),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(start = 60.dp, end = 40.dp)
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(start = horizontalMarginDescription, end = horizontalMarginDescription, bottom = 30.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {

        if(screenSize.value.first > 1200f) {
            Image(
                painter = painterResource(Res.drawable.business_low),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(start = 60.dp, end = 60.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(4f)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(Res.string.body_two_paragraph_one_one))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_two_paragraph_one_two))
                    }
                    append(stringResource(Res.string.body_one_paragraph_three_three))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_two_paragraph_one_four))
                    }
                    append(stringResource(Res.string.body_two_paragraph_one_five))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.body_two_paragraph_one_six))
                    }
                    append(stringResource(Res.string.body_two_paragraph_one_seven))
                },
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = stringResource(Res.string.body_two_paragraph_two),
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = stringResource(Res.string.body_two_paragraph_three),
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )
        }
    }
}