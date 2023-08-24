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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.wantech.mytasker.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTime(
    modifier: Modifier = Modifier,
    startTime: LocalTime? = null,
    endTime: LocalTime? = null,
    onStartTimeChange: (LocalTime) -> Unit,
    onEndTimeChange: (LocalTime) -> Unit
) {
    val startDateState = rememberMaterialDialogState()
    val endDateState = rememberMaterialDialogState()
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh.mma")
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
                text = stringResource(R.string.starts),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.ends),
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
                },
                label = {
                    if (startTime != null) {

                        Text(text = startTime.format(formatter))
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = stringResource(id = R.string.add)
                        )
                    }
                },
            )

            ElevatedAssistChip(onClick = {
                endDateState.show()
            },
                label = {
                    if (endTime != null) {
                        Text(text = endTime.format(formatter))
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = stringResource(id = R.string.add)
                        )
                    }

                })


        }

        MaterialDialog(dialogState = startDateState,
            shape = RoundedCornerShape(20.dp),
            buttons = {
                positiveButton(
                    text = stringResource(id = R.string.ok),
//                textStyle = MaterialTheme.typography.labelMedium
                    onClick = {

                    }
                )
                negativeButton(
                    text = stringResource(id = R.string.cancel)
                )
            }) {
            timepicker(
                title = "Select Start Time",
                onTimeChange = onStartTimeChange,
            )
        }
        MaterialDialog(dialogState = endDateState,
            shape = RoundedCornerShape(20.dp),
            buttons = {
                positiveButton(text = stringResource(R.string.ok), onClick = {

                })
                negativeButton(text = stringResource(R.string.cancel))
            }) {

            timepicker(
                title = stringResource(R.string.select_end_time),
                onTimeChange = onEndTimeChange,
                timeRange = (startTime ?: LocalTime.now())..LocalTime.MAX
            )
        }


    }
}

@Composable
fun TaskTime(
    modifier: Modifier = Modifier,

) {

}
