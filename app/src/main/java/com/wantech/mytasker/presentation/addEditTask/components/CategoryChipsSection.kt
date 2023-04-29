package com.wantech.mytasker.presentation.addEditTask.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SportsFootball
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CategoryChipsSection(
    modifier: Modifier = Modifier,
    categories: List<Category> = listOf(
        Category(categoryName = "Education", icon = Icons.Default.School),
        Category(categoryName = "Sports", icon = Icons.Default.SportsFootball),
        Category(categoryName = "Friends", icon = Icons.Default.Handshake),
        Category(categoryName = "Meetings", icon = Icons.Default.MeetingRoom),
        Category(categoryName = "General", icon = Icons.Default.Task),
    )
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Category",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        SelectCategories(categories,
            onCategoryClick = {

            })


    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SelectCategories(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit
) {
    var selectedCategoryIndex = remember {
        mutableStateOf(0)
    }
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        crossAxisAlignment = FlowCrossAxisAlignment.Center,
        crossAxisSpacing = 6.dp, mainAxisSpacing = 6.dp
    ) {
        ElevatedAssistChip(onClick = { /*TODO*/ },
            label = {
                Text("All")
            })
        categories.forEach { category: Category ->
            ElevatedAssistChip(onClick = { onCategoryClick(category) },
                label = {
                    Text(text = category.categoryName)
                },
                leadingIcon = {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.categoryName
                    )
                })

        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoryPreview() {
    CategoryChipsSection()
}

data class Category(val categoryName: String, val icon: ImageVector)