package com.example.artspace

import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {

    var position by remember {
        mutableStateOf(1)
    }
    val dataId = getId(position)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(top = 70.dp)
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .background(Color.White)
                    .padding(30.dp)
            ) {
                Image(painter = painterResource(dataId[0]), contentDescription = "1")
            }
        }
        ArtInfo(dataId)
        ButtonInRow(
            onPreClick = {
                if (position >= 1) position-- else position = 6
            }, onNextClick = {
                if (position <= 6) position++ else position = 1
            })
    }
}

@Composable
fun ArtInfo(artInfo: Array<Int>, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .padding(top = 50.dp)
            .background(colorResource(id = R.color.grayArtInfo))
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ShowText(
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(artInfo[1])
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ShowText(
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                    text = stringResource(artInfo[2]),
                    Modifier.padding(end = 7.dp),
                )
                ShowText(
                    style = TextStyle(fontSize = 20.sp),
                    text = "(${stringResource(artInfo[3])})",
                )

            }
        }
    }

}

@Composable
fun ShowText(style: TextStyle, text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = style,
        modifier = modifier
    )
}

@Composable
fun ButtonInRow(onPreClick: () -> Unit, onNextClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp), Arrangement.SpaceAround
    ) {
        SetButton(onClick = onPreClick, str = stringResource(R.string.previous))
        SetButton(onClick = onNextClick, str = stringResource(R.string.next))

    }
}

@Composable
fun SetButton(onClick: () -> Unit, str: String = "") {

    val btnStyle = Modifier.width(150.dp)
    Button(
        onClick = onClick,
        modifier = btnStyle
    ) {
        Text(str)
    }
}


fun getId(position: Int = 1): Array<Int> {
    val allId: Array<Int> = when (position) {
        1 -> arrayOf(R.drawable.img_01, R.string.ArtNo1, R.string.ArtistNo1, R.string.YearNo1)
        2 -> arrayOf(R.drawable.img_02, R.string.ArtNo2, R.string.ArtistNo2, R.string.YearNo2)
        3 -> arrayOf(R.drawable.img_03, R.string.ArtNo3, R.string.ArtistNo3, R.string.YearNo3)
        4 -> arrayOf(R.drawable.img_04, R.string.ArtNo4, R.string.ArtistNo4, R.string.YearNo4)
        5 -> arrayOf(R.drawable.img_05, R.string.ArtNo5, R.string.ArtistNo5, R.string.YearNo5)
        else -> arrayOf(
            R.drawable.img_06,
            R.string.ArtNo6,
            R.string.ArtistNo6,
            R.string.YearNo6
        )

    }
    return allId
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}