package com.scode.categorizedlazycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scode.categorizedlazycomposeapp.ui.theme.CategorizedlazyComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val namesList = names.map {
            Category(
                name = it.key.toString(),
                items = it.value,
                color = colorList.random()
            )
        }
        setContent {
            CategorizedlazyComposeAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CategorizedLazyColumn(categories = namesList)
                }
            }
        }
    }
}

val colorList = listOf(
    Color(0xFFD0BCFF),
    Color(0xFFB8E994),
    Color(0xFF8EC5FC),
    Color(0xFFFFD180),
    Color(0xFFEFB8C8)
)

data class Category(
    val name: String,
    val items: List<String>,
    val color: Color
)

@Composable
private fun CategoryHeader(
    text: String, color: Color
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(16.dp)
    )
}

@Composable
private fun CategoryItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(categories: List<Category>, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(category.name,category.color)
            }
            items(category.items) { text ->
                CategoryItem(text)
            }
        }
    }
}