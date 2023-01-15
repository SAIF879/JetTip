package com.example.jettip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettip.ui.theme.JetTipTheme
import com.example.jettip.ui.theme.Shapes
import com.example.jettip.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

      val amount by remember {
          mutableStateOf(0)
      }

MyApp {

    generateBox(
        color = colorResource(id = R.color.purple_200),
        height =150,
        padding =20,
        borderWidth =0,
        borderColor =Color.Black,
        shape = RoundedCornerShape(10.dp)
    ) {

       generateText(content = "Total Per Person", fontWeight = FontWeight.SemiBold, color = Color.Black, fontSize =25.sp )
        generateText(content ="₹ $amount" , fontWeight =FontWeight.ExtraBold , color =Color.Black , fontSize =50.sp )
    }

   Spacer(modifier = Modifier.height(10.dp))

    generateBox(color = Color.White,
        height =300 ,
        padding = 15,
        borderWidth = 3,
        borderColor = Color.Black,
        shape = RectangleShape) {
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
            .heightIn(height.dp)
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
@Composable
fun generateText(
    content : String,
    fontWeight: FontWeight,
    color: Color,
    fontSize : TextUnit
){
    Text(text = content , fontWeight =fontWeight, color =color,fontSize = fontSize)
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