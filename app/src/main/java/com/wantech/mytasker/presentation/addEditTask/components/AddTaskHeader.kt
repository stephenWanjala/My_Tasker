package com.wantech.mytasker.presentation.addEditTask.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddTaskHeader(
    modifier: Modifier = Modifier,
    closePage: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Create Task",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        IconButton(
            onClick = closePage,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(imageVector = Icons.Outlined.Close, contentDescription = "close")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskAppBar(modifier: Modifier = Modifier, closePage: () -> Unit) {
    CenterAlignedTopAppBar(modifier = modifier, title = {
        Text(
            text = "Create Task",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    },
        actions = {
            IconButton(
                onClick = closePage,
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "close")
            }
        })
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevAddTaskHeader() {
    AddTaskHeader {

    }
}