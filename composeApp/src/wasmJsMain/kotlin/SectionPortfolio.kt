import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
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
fun SectionPortfolio(screenSize: MutableState<Pair<Int, Int>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            text = stringResource(Res.string.title_portfolio),
            color = Color.Black,
            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold)
        )

        // BFY
        Image(
            painter = painterResource(Res.drawable.logo_bfy),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )
        Image(
            painter = painterResource(Res.drawable.app_b_fy),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // Business UK
        Image(
            painter = painterResource(Res.drawable.logo_santander_business_uk),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )
        Image(
            painter = painterResource(Res.drawable.app_santander_business_uk),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // FGM
        Image(
            painter = painterResource(Res.drawable.logo_fgm),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )
        Image(
            painter = painterResource(Res.drawable.app_fgm),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // Santander UK
        Image(
            painter = painterResource(Res.drawable.logo_santander_uk),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )
        Image(
            painter = painterResource(Res.drawable.app_santander_uk),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )


        // Control Gas
        Image(
            painter = painterResource(Res.drawable.logo_controlgas),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_controlgas),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // Pride Quiz
        Image(
            painter = painterResource(Res.drawable.logo_pride),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )
        Image(
            painter = painterResource(Res.drawable.app_pride),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // Santander DE
        Image(
            painter = painterResource(Res.drawable.logo_santander_alemania),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_santander_alemania),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // consolas
        Image(
            painter = painterResource(Res.drawable.logo_console),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_console),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )


        // Biocryptology
        Image(
            painter = painterResource(Res.drawable.logo_biocryptology),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_biocryptology),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // Edad Perruna
        Image(
            painter = painterResource(Res.drawable.logo_calculadora_perruna),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_calculadora_perruna),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )


        // Critizen
        Image(
            painter = painterResource(Res.drawable.logo_critizen),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )
        Image(
            painter = painterResource(Res.drawable.app_critizen),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // ThRanking WEB
        Image(
            painter = painterResource(Res.drawable.logo_theranking),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_theranking),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )

        // Dale Al Play
        Image(
            painter = painterResource(Res.drawable.logo_dalealplay),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .padding(top = 50.dp)
                .height(80.dp)
        )
        Image(
            painter = painterResource(Res.drawable.app_dalealplay),
            contentDescription = stringResource(Res.string.content_description),
            modifier = Modifier
                .height(300.dp)
        )
    }
}