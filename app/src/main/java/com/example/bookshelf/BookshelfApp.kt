package com.example.bookshelf

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.ui.BookViewModel
import com.example.bookshelf.ui.DetailScreen
import com.example.bookshelf.ui.HomeScreen

enum class BookshelfApp (@StringRes val title: Int){
    Home(title = R.string.home),
    Detail(title = R.string.detail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopAppBar(
    currentScreen: BookshelfApp,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    CenterAlignedTopAppBar(
        title = { Text("Book Search")},
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}


@Composable
fun BSApp(
    viewModel: BookViewModel = viewModel(factory = BookViewModel.Factory),
    navController: NavHostController = rememberNavController()
){
    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BookshelfApp.valueOf(
        backstackEntry?.destination?.route ?: BookshelfApp.Home.name
    )
    Scaffold(
        topBar = {
            BookshelfTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BookshelfApp.Home.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = BookshelfApp.Home.name){
                HomeScreen(
                    bookUiState = viewModel.bookUiState,
                    bookViewModel = viewModel,
                    onclick = {
                        viewModel.currentBook = it
                        navController.navigate(BookshelfApp.Detail.name)
                    },
                    retryAction = viewModel::getBooks,
                    modifier = Modifier
                )
            }
            composable(route = BookshelfApp.Detail.name){
                DetailScreen(
                    bookViewModel = viewModel,
                    modifier = Modifier
                )
            }
        }
    }
}