package com.example.jettip

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettip.components.generateInputField
import com.example.jettip.ui.theme.JetTipTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

      val amount by remember {
          mutableStateOf(0.0)
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
        billForm(){billAmt->
            Log.d("amt", "onCreate: ${billAmt.toInt()*100} ")

        }

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


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun billForm(modifier: Modifier=Modifier,onValChange : (String) -> Unit = {}){
    val mContext = LocalContext.current
    val totalbillState = remember {
        mutableStateOf("0")
    }

    val validState = remember(totalbillState.value) {
        totalbillState.value.trim().isNotEmpty()
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    generateInputField(
        mainString = totalbillState,
        isEnabled =true ,
        padding =10 ,
        isSingleLine =true ,
        isReadOnly =false ,
        context = mContext,
        onAction = KeyboardActions{
            if (!validState)return@KeyboardActions
           onValChange(totalbillState.value.trim())
            keyBoardController?.hide()

        }
    ) {
        Text(text = "box")
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