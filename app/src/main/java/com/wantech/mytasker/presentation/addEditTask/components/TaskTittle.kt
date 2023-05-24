package com.wantech.mytasker.presentation.addEditTask.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wantech.mytasker.R

@Composable
fun TaskTittle(
    modifier: Modifier = Modifier,
    tittle: String,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = stringResource(R.string.task_tittle),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        TaskEditText(
            placeHolder = stringResource(R.string.tittle),
            onTextChange = onTextChange,
            text = tittle
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditText(
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    placeHolder: String,
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    singleLine: Boolean = true,
    onTextChange: (String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = { value ->
            onTextChange(value)
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
    body: String,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = stringResource(R.string.task_description),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        TaskEditText(
            placeHolder = stringResource(R.string.task_body),
            text = body,
            maxLines = 6,
            singleLine = false,
            keyboardOptions = KeyboardOptions().copy
                (imeAction = ImeAction.Done),
            onTextChange = onTextChange
        )
    }
}


@Composable
fun CreateTaskButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    enabled: Boolean=false,
    onclick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onclick,
        shape = RoundedCornerShape(8.dp),
        enabled =enabled
    ) {
        Text(
            text = buttonText, modifier = Modifier,
            textAlign = TextAlign.Center
        )
    }
}

