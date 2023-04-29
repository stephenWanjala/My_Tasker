package com.wantech.mytasker.presentation.addEditTask.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTime(
    modifier: Modifier = Modifier,
    taskTimeState: MutableState<TaskTimeState> = remember { mutableStateOf(TaskTimeState()) }
) {
    val startDateState = rememberMaterialDialogState()
    val endDateState = rememberMaterialDialogState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Starts",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Ends",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedAssistChip(
                onClick = {
                    startDateState.show()
                    taskTimeState.value = taskTimeState.value.copy(starTimeChipClicked = true)
                },
                label = {
                    if (taskTimeState.value.starTimeChipClicked) {

                        Text(text = taskTimeState.value.selectedStartDateTime.format(taskTimeState.value.formatter))
                    } else {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "add")
                    }
                },
            )

            ElevatedAssistChip(onClick = {
                endDateState.show()
            },
                label = {
                    if (taskTimeState.value.endTimeChipClicked) {
                        Text(text = taskTimeState.value.selectedEndDateTime.format(taskTimeState.value.formatter))
                    } else {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "add")
                    }

                })


        }

        MaterialDialog(dialogState = startDateState,
            shape = RoundedCornerShape(20.dp),
            buttons = {
                positiveButton(
                    text = "Ok",
//                textStyle = MaterialTheme.typography.labelMedium
                    onClick = {
                        taskTimeState.value =
                            taskTimeState.value.copy(
                                selectedStartDateTime = taskTimeState.value.selectedStartTime.atDate(
                                    taskTimeState.value.selectedStartDate
                                )
                            )
                        taskTimeState.value = taskTimeState.value.copy(endTimeChipClicked = true)
                    }
                )
                negativeButton(
                    text = "Cancel"
                )
            }) {
            timepicker(
                title = "Select Start Time",
                onTimeChange = { localTime ->
                    taskTimeState.value = taskTimeState.value.copy(selectedStartTime = localTime)
                    taskTimeState.value = taskTimeState.value.copy(endTimeChipClicked = true)
                },
            )
        }
        MaterialDialog(dialogState = endDateState,
            shape = RoundedCornerShape(20.dp),
            buttons = {
                positiveButton(text = "Ok", onClick = {
                    taskTimeState.value =
                        taskTimeState.value.copy(
                            selectedEndDateTime = taskTimeState.value.selectedEndTime.atDate(
                                taskTimeState.value.selectedEndDate
                            )
                        )
                    taskTimeState.value = taskTimeState.value.copy(endTimeChipClicked = true)
                })
                negativeButton(text = "Cancel")
            }) {
            timepicker(
                title = "Select End Time",
                onTimeChange = { localTime ->
                    taskTimeState.value = taskTimeState.value.copy(selectedEndTime = localTime)
                    taskTimeState.value = taskTimeState.value.copy(endTimeChipClicked = true)
                },
                timeRange = taskTimeState.value.selectedStartTime..LocalTime.MAX
            )
        }


    }
}

data class TaskTimeState(
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM,h.mma"),
    val selectedStartTime: LocalTime = LocalTime.now(),
    val selectedStartDate: LocalDate = LocalDate.now(),
    val selectedEndTime: LocalTime = LocalTime.now(),
    val selectedEndDate: LocalDate = LocalDate.now(),
    val selectedStartDateTime: LocalDateTime = LocalDateTime.now(),
    val selectedEndDateTime: LocalDateTime = LocalDateTime.now(),
    val starTimeChipClicked: Boolean = false,
    val endTimeChipClicked: Boolean = false
)


@Preview(showBackground = true)
@Composable
fun ChipSe() {
    TaskTime()
}