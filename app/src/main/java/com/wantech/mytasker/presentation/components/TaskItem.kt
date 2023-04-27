package com.wantech.mytasker.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wantech.mytasker.domain.model.Task
import com.wantech.mytasker.util.toDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(modifier: Modifier = Modifier, task: Task, onclickTask: (Task) -> Unit) {
    var completeStatusIcon by remember {
        mutableStateOf(Icons.Outlined.Circle)
    }

    LaunchedEffect(key1 = task.completed) {
        if (task.completed) completeStatusIcon =
            Icons.Default.CheckCircle else Icons.Outlined.Circle
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task.startTime.toDate(),
            fontWeight = FontWeight.Bold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = completeStatusIcon, contentDescription = null, modifier.size(16.dp))
            Divider(
                modifier = Modifier.width(6.dp), thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    onClick = {
                        onclickTask(task)
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = task.taskTittle,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = task.taskBody)
                        Text(
                            text = "${task.startTime.toDate("hh:mm")} - ${task.endTime.toDate("hh:mm")}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskItem(task = Task(
        taskTittle = "Walk around", taskBody = "Walk around Msu area",
        startTime = System.currentTimeMillis(), endTime = System.currentTimeMillis() + 200000
    ), onclickTask = {})
}

fun Modifier.fillOnComplete(tasKComplete: Boolean, backGround: Color): Modifier = composed {
    if (tasKComplete) {
        this
            .border(
                border = BorderStroke(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = CircleShape
            )
            .background(color = backGround, shape = CircleShape)
    } else {
        this
            .border(
                border = BorderStroke(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                shape = CircleShape
            )
    }
}