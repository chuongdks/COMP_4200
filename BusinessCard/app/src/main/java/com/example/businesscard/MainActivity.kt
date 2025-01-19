package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscard.ui.theme.BusinessCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                //
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(2.dp, Color.Yellow)
            .background(Color(0xFFd2e8d4))
    ) {
        TitleCard(
            name = "Chuong Hoang Pham",
            title = "CEO of SEX",
            modifier = Modifier
                .align(Alignment.Center)

        )

        ContactInfo(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun TitleCard(name: String = "Insert name here",
              title: String = "Insert Title Here",
              modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.ic_launcher_background)
    Column(
        //
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .border(2.dp, Color.Blue)
            .padding(15.dp) // fillMaxWidth()
    ) {
        //
        Box(
            modifier = Modifier
                .size(104.dp)
                .background(Color(0xFF073042)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = "Let's see Paul Allen Card",
                modifier = modifier.border(2.dp, Color.Red)
            )
        }
        Text(
            text = name,
            modifier = modifier.border(2.dp, Color.Red)
        )
        Text(
            text = title,
            modifier = modifier.border(2.dp, Color.Red)
        )
    }
}

@Composable
fun ContactInfo(phone: String = "+69 (69) 696 696",
                social: String = "@fakenews",
                email: String = "mymail@gmail.com",
                modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(2.dp, Color.Magenta)
            .padding(17.dp)
    ) {
        // Phone Row
        ContactRow(
            icon = Icons.Default.Call,
            contentDescription = "Phone",
            text = phone
        )
        // Social Row
        ContactRow(
            icon = Icons.Default.Share,
            contentDescription = "Social",
            text = social
        )
        // Email Row
        ContactRow(
            icon = Icons.Default.Email,
            contentDescription = "Email",
            text = email
        )
    }
}

@Composable
fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    text: String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(45.dp) // Ensure each icon size is the same
                .padding(end = 16.dp),
            tint = Color(0xFF006d3b)
        )
        Text(
            text = text,
            textAlign = TextAlign.Left,
            modifier = Modifier.weight(1f) // Ensure text takes up the right amount of space
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}