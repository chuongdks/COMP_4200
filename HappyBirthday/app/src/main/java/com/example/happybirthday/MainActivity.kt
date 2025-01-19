package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuad()
                }
            }
        }
    }
}

//@Composable
//fun GreetingText(header: String, content1: String, content2: String, modifier: Modifier = Modifier) {
//    val image = painterResource(R.drawable.bg_compose_background)
//    Column (
//        verticalArrangement = Arrangement.Center,
//        modifier = modifier
//    ) {
//        Image(
//            painter = image,
//            contentDescription = "Picture of ",
//            modifier = Modifier.fillMaxWidth()
//        )
//        Text(
//            text = header,
//            fontSize = 24.sp,
//            lineHeight = 116.sp,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .padding(16.dp)
//        )
//        Text(
//            text = content1,
//            modifier = Modifier
//                .padding(
//                    start = 16.dp,
//                    end = 16.dp
//                ),
//            textAlign = TextAlign.Justify
//        )
//        Text(
//            text = content2,
//            modifier = Modifier
//                .padding(16.dp),
//            textAlign = TextAlign.Justify
//        )
//    }
//}

//@Composable
//fun TaskCompleteIcon (content1: String, content2: String, modifier: Modifier = Modifier) {
//    val image = painterResource(R.drawable.ic_task_completed)
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        Image(
//            painter = image,
//            contentDescription = "Icon"
//        )
//        Text (
//            text = content1,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .padding(
//                    top = 24.dp,
//                    bottom = 16.dp
//                )
//        )
//        Text (
//            text = content2,
//            fontSize = 16.sp,
//        )
//    }
//}

 @Composable
fun ComposeQuad () {
     Column (modifier = Modifier.fillMaxSize()) {
         Row(Modifier.weight(1f)) {
             ComposeInfoCard (
                 title = "Text composable",
                 description = "Displays text and follows the recommended Material Design guidelines.",
                 backgroundColor = Color(0xFFEADDFF),
                 modifier = Modifier.weight(2f)
             )
             ComposeInfoCard (
                 title = "Row composable",
                 description = "A layout composable that places its children in a horizontal sequence",
                 backgroundColor = Color(0xFFD0BCFF),
                 modifier = Modifier.weight(1f)
             )
         }
         Row(Modifier.weight(2f)) {
             ComposeInfoCard (
                 title = "Image composable",
                 description = "Creates a composable that lays out and draws a given Painter class object.",
                 backgroundColor = Color(0xFFB69DF8),
                 modifier = Modifier.weight(1f)
             )
             ComposeInfoCard (
                 title = "Column composable",
                 description = "A layout composable that places its children in a vertical sequence.",
                 backgroundColor = Color(0xFFF6EDFF),
                 modifier = Modifier.weight(1f)
             )
         }
     }
}

// Info card of the Compose, Low Coupling
@Composable
private fun ComposeInfoCard(
    modifier: Modifier = Modifier,
    title: String = "Enter Title here",
    description: String = "Enter Description here",
    backgroundColor: Color = Color(0xFFEADDFF)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp), // Specification 1.
        horizontalAlignment = Alignment.CenterHorizontally, // Specification 2
        verticalArrangement = Arrangement.Center, // Specification 2
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    bottom = 16.dp // Specification 3
                )
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify // Specification 4
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        ComposeQuad()
    }
}