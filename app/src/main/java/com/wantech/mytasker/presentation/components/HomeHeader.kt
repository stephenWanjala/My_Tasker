package com.wantech.mytasker.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person, contentDescription = "profile",
                )
            }
        },
        actions = {
            BadgedBox(
                badge = {
                    Badge(
                        modifier = Modifier
                            .offset(x = (-6).dp, y = 7.dp)
                    ) {
                        Text(text = "5")
                    }
                },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "notifications"
                )
            }
        }
    )

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}