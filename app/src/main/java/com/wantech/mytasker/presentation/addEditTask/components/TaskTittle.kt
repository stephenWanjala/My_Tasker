package com.wantech.mytasker.presentation.addEditTask.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TaskTittle(
    modifier: Modifier = Modifier,
    tittle: MutableState<TextFieldValue>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = "Task Tittle", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        TaskEditText(placeHolder = "Tittle...", state = tittle)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditText(
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    placeHolder: String,
    state: MutableState<TextFieldValue>,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    singleLine: Boolean = true
) {

    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        placeholder = {
            Text(text = placeHolder, textAlign = TextAlign.Center)
        },
        shape = RoundedCornerShape(20.dp),
        maxLines = maxLines,
        modifier = modifier.padding(16.dp),
        keyboardOptions = keyboardOptions,
        singleLine = singleLine

    )

}

@Composable
fun TaskBody(
    modifier: Modifier = Modifier,
    body: MutableState<TextFieldValue>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = "Task Description", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        TaskEditText(
            placeHolder = "Task Body...", state = body, maxLines = 6, singleLine = false,
            keyboardOptions = KeyboardOptions().copy
                (imeAction = ImeAction.Done)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TaskEditTextPrev() {
    val text = remember {
        mutableStateOf(TextFieldValue(""))
    }
    TaskEditText(placeHolder = "Add Task", state = text)
}