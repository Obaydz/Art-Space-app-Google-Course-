package com.example.artspace

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

var currentArtwork by mutableStateOf(1)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(
                    bottomBar = {Art_botton()}
                ) {
                    innerPadding -> ArtSpace(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun content(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val horizontalPadding = if (maxWidth < 600.dp) {
            20.dp // Phone
        } else {
            400.dp // Tablet
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(start = horizontalPadding, end = horizontalPadding)
        ) {
            var img = R.drawable.alloutas_notes
            var title = "Allouta's Notes"
            var artist = "Obayd"
            var year = 2026

            if (currentArtwork == 1) {
                img = R.drawable.alloutas_notes
                title = "Allouta's Notes"
                artist = "Obayd"
                year = 2026
            } else if (currentArtwork == 2) {
                img = R.drawable.art1
                title = "The night sky"
                artist = "Otamendi"
                year = 2020
            } else {
                img = R.drawable.art2
                title = "The beautiful city"
                artist = "Alber Doiski"
                year = 2021
            }

            Art(
                img = img
            )

            Spacer(
                modifier = Modifier.height(50.dp)
            )

            ArtDesc(
                title = title,
                artist = artist,
                year = year
            )
        }
    }
}

@Composable
fun ArtDesc(
    title: String,
    artist: String,
    year: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(Color.LightGray)
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = title,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$artist ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("($year)")
                }
            },
            fontSize = 20.sp
        )
    }
}


@Composable
fun Art_botton(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Button(
            onClick = {
                if (currentArtwork == 1){
                    currentArtwork = 2
                } else if (currentArtwork == 2){
                    currentArtwork = 3
                } else {
                    currentArtwork = 1
                }
            },
            modifier = Modifier
                .width(150.dp)
        ) {
            Text("Précédent")
        }

        Button(
            onClick = {
                if (currentArtwork == 1){
                    currentArtwork = 3
                } else if (currentArtwork == 2){
                    currentArtwork = 1
                } else {
                    currentArtwork = 2
                }
            },
            modifier = Modifier
                .width(150.dp)
        ) {
            Text("Suivant")
        }
    }
}

@Composable
fun Art(
    @DrawableRes img : Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = modifier
                    .border(25.dp, Color.White)
                    .height(600.dp)
                    .shadow(10.dp)
                    .fillMaxSize()

            )
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    ArtSpaceTheme {
        content()
    }
}