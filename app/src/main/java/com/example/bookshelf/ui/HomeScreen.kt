package com.example.bookshelf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.model.Book

@Composable
fun HomeScreen(
    bookUiState: BookUiState,
    bookViewModel: BookViewModel,
    onclick: (Book) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold()
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (bookUiState) {
                is BookUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                is BookUiState.Success -> PhotoGridScreen(
                    bookUiState.books,
                    modifier = Modifier,
                    contentPadding = PaddingValues(14.dp),
                    onValueChange = { bookViewModel.changeUserGuess(it) },
                    value = bookViewModel.userInput,
                    onKeyBoardDone = { bookViewModel.getBooks(bookViewModel.userInput) },
                    onclick = onclick
                )

                is BookUiState.Error -> ErrorScreen(
                    retryAction = retryAction,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) { Text("Loading") }


}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("Error")
        Button(
            onClick = retryAction
        ) { Text("Retry") }
    }
}

@Composable
fun BookData(books: Book, onBookClick: (Book) -> Unit, modifier: Modifier) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = books.volumeInfo.imageLinks?.thumbnail?.replace("http:", "https:")
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            onClick = { onBookClick(books) }
        ) {
            Text(
                text = books.volumeInfo.title,
                modifier = Modifier.padding(16.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PhotoGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onValueChange: (String) -> Unit,
    value: String,
    onKeyBoardDone: () -> Unit,
    onclick: (Book) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            label = { Text("Search") },
            keyboardActions = KeyboardActions(onDone = { onKeyBoardDone() })
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            contentPadding = contentPadding
        ) {
            items(items = books, key = { book -> book.id }) { book ->
                BookData(books = book, onBookClick = onclick, modifier = Modifier)
            }
        }
        Text("what")
    }


}




