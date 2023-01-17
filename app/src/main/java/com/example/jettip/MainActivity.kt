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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Minimize
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettip.components.generateCircleButton
import com.example.jettip.components.generateInputField
import com.example.jettip.ui.theme.JetTipTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mcontext = LocalContext.current
      var amount by remember {
          mutableStateOf(0F)
      }

            var people by remember {
                mutableStateOf(1)
            }

            var sliderPositionState by remember {
                mutableStateOf(0F)
            }
            var tipAmountstate by remember {
                mutableStateOf(0.0)
            }
            val totalbillState = remember {
                mutableStateOf("0")
            }
 val tipPercentage = (sliderPositionState *100).toInt()

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
        billForm(Modifier,totalbillState){billAmt->
            Log.d("amt", "onCreate: ${billAmt.toInt()*100} ")
        }
        generateContainer(size = 70, padding =30 , elevation =5 ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                generateText(content = "Split", fontWeight =FontWeight.Black , color =Color.Black , fontSize =25.sp )
                Spacer(modifier = Modifier.width(70.dp))
                generateCircleButton(
                    context = mcontext ,
                    people = people,
                    size = 50,
                    padding = 0,
                    border = 0,
                    imageVector =Icons.Default.Remove ,
                ){
                  if(people<2){people=1}
                    else {people-=1}
                }
//                generateText(content = "$people", fontWeight =FontWeight.SemiBold , color =Color.Black , fontSize =10.sp ,)
                Text(
                    text = "$people",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(10.dp)
                )
                generateCircleButton(
                    context = mcontext,
                    people =people ,
                    size = 50,
                    padding = 0,
                    border = 0,
                    imageVector =Icons.Default.Add,
                ){
                    people+=1
                }


            }
        }
        generateContainer(size = 30, padding = 29, elevation =5 ) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "TIP  -->", fontSize = 25.sp, fontWeight = FontWeight.Bold, color=Color.Red
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "$ ${tipAmountstate}", fontSize = 25.sp,fontWeight = FontWeight.SemiBold)
            }
        }
        generateContainer(size = 70, padding =10 , elevation =5 ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
                Text(text = "$tipPercentage %")
                Slider(value = sliderPositionState, onValueChange ={newVal ->
                    sliderPositionState = newVal
//                    tipAmountstate.value = calculate()
                    tipAmountstate = calculateTip(totalbillState.value.toDouble(),tipPercentage)
                    amount = CalculateTotalPerPerson(totalbillState.value.toDouble(), tipPercentage , people)
                    Log.d("tip", "onCreate: ${tipAmountstate}")
                    Log.d("slider", "onCreate: $sliderPositionState")
                },
                steps = 5
                    )
            }
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
        ) {
            content()
        }

    }
}

@Composable
fun generateContainer(
    size : Int,
    padding : Int,
    elevation : Int,
    content: @Composable () -> Unit
){
    Box(
        modifier = Modifier
            .padding(padding.dp)
            .height(size.dp)
            .fillMaxWidth(),
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
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
fun billForm(modifier: Modifier=Modifier, totalbillState : MutableState<String>, onValChange : (String) -> Unit = {}){
    val mContext = LocalContext.current

    val validState = remember(totalbillState) {
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

fun calculateTip(totalBill : Double , tipPercentage : Int) : Double{
   return if (totalBill>1 && totalBill.toString().isNotEmpty())
       (totalBill * tipPercentage) / 100
        else 0.0
}

fun CalculateTotalPerPerson(totalAmount : Double , tipPercentage: Int , people : Int) : Float{
    val bill = (calculateTip(totalAmount,tipPercentage) + totalAmount) / people
    return bill.toFloat()
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