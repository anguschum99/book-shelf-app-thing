package com.example.bookshelf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DetailScreen(
    bookViewModel: BookViewModel,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val imageUrl = bookViewModel.currentBook?.volumeInfo?.imageLinks?.thumbnail?.replace("http:", "https:")
        //Text(text = "Title", style = MaterialTheme.typography.titleLarge)
        Text(text = bookViewModel.currentBook?.volumeInfo?.title.toString(),style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))
        //Text(text = "Author", style = MaterialTheme.typography.titleLarge)
        Text(text = bookViewModel.currentBook?.volumeInfo?.authors?.get(0).toString(), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(10.dp))

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Publisher", style = MaterialTheme.typography.titleLarge)
        Text(text = bookViewModel.currentBook?.volumeInfo?.publisher.toString(), style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Published Date", style = MaterialTheme.typography.titleLarge)
        Text(text = bookViewModel.currentBook?.volumeInfo?.publishedDate.toString(), style = MaterialTheme.typography.titleSmall)

        Text(text = "Description", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = bookViewModel.currentBook?.volumeInfo?.description.toString(), style = MaterialTheme.typography.bodyMedium)
    }
}





