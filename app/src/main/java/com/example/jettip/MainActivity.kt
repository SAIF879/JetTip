package com.example.jettip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettip.ui.theme.JetTipTheme
import com.example.jettip.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

MyApp {

    generateBox(
        color =Color.Red,
        height =200,
        padding =20,
        borderWidth =3,
        borderColor =Color.Black,
        shape = RoundedCornerShape(10.dp)
    ) {

        Text(text = "hello world", fontSize = 32.sp, fontWeight = FontWeight.Bold)
    }
}
        }
    }
}


@Composable
fun MyApp(content : @Composable () -> Unit){
    JetTipTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
          Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center
          ) {
              content()
          }
        }
    }
}

@Composable
fun generateBox(color: Color,
                height : Int,
                padding : Int,
                borderWidth : Int,
                borderColor : Color,
                shape: Shape,
                content: @Composable () -> Unit,
){
    Card(
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth()
            .padding(padding.dp)
        ,
        backgroundColor = color,
        elevation = 4.dp,
        border = BorderStroke(borderWidth.dp,borderColor),
        shape = shape

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }

    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTipTheme {
       MyApp {
           Text(text = "hello world")
       }
    }
}