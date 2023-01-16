package com.example.jettip.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// call this @Composable fun() to generate an input Field
@Composable
fun generateInputField(
    mainString : MutableState<String>,
    isEnabled : Boolean,
    padding : Int ,
    isSingleLine : Boolean,
    isEditable : Boolean,
    icon : ImageVector,
    context : Context,
    callFunctions : @Composable () ->Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction : KeyboardActions = KeyboardActions.Default
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
        readOnly = isEditable,
        leadingIcon = {
            IconButton(onClick = { Toast.makeText(context, "u clicked : )", Toast.LENGTH_SHORT).show() }) {
                Icon(imageVector = icon, contentDescription ="icon" )
            }
        },
      keyboardOptions = KeyboardOptions(
          keyboardType = keyboardType,
          imeAction = imeAction
      ),
        keyboardActions = onAction,
        )
}
