package com.example.jettip.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun generateInputField(
    valueState : MutableState<String>,
    isEnabled : Boolean,
    isSingleLine : Boolean,
    labelId : String
){
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        enabled = isEnabled,
        singleLine = isSingleLine,
        label = { Text(text = labelId)},
        leadingIcon = {}


    )
}