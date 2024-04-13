import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import personalpage.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SectionCompanies() {
    Column(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = stringResource(Res.string.title_company),
            color = Color.Black,
            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold)
        )

        Row(
            modifier = Modifier
                .padding(60.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.logo_bfy),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.logo_santander_uk),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.talento_mobile),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.logo_critizen),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(120.dp)
                    .weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .padding(60.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.logo_theranking),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.logo_dalealplay),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.logo_apilink),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.panaderia),
                contentDescription = stringResource(Res.string.content_description),
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
            )
        }

    }
}