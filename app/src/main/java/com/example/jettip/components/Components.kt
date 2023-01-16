package com.example.jettip.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// call this @Composable fun() to generate an input Field
@Composable
fun generateInputField(
    mainString : MutableState<String>,
    isEnabled : Boolean,
    padding : Int,
    isSingleLine : Boolean,
    isReadOnly : Boolean,
    context : Context,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction : KeyboardActions = KeyboardActions.Default,
    callFunctions : @Composable () ->Unit,
){
    OutlinedTextField(
        value = mainString.value ,
        onValueChange = {
          mainString.value = it
        },
        modifier = Modifier.padding(padding.dp),
        enabled =  isEnabled,
        singleLine = isSingleLine,
        label = {
            // call a Text for title
                callFunctions()
        },
        readOnly = isReadOnly,
        leadingIcon = {
//            IconButton(onClick = { Toast.makeText(context, "u clicked : )", Toast.LENGTH_SHORT).show() }) {
//             Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription ="moeny_dis" )
//            }
                      Icon(imageVector = Icons.Rounded.Money, contentDescription ="money_dis", tint = Color.Black)
        },
      keyboardOptions = KeyboardOptions(
          keyboardType = keyboardType,
          imeAction = imeAction
      ),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black ,
            unfocusedLabelColor = Color.Black
        )
        )
}
