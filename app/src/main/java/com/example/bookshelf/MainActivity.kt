package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookshelf.ui.BookViewModel
import com.example.bookshelf.ui.theme.BookshelfTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookUiState
import com.example.bookshelf.ui.HomeScreen
import com.example.bookshelf.ui.components.TopSearchBar


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshelfTheme {
                Scaffold(
                    modifier = Modifier
                ) { innerPadding ->
                    val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)
                    BSApp()
                }
            }
        }
    }
}

@Composable
fun BookData(
    bookUiState: BookUiState,
    modifier: Modifier = Modifier,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("swag")

    }


}

