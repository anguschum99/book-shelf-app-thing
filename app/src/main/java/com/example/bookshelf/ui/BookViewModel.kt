package com.example.bookshelf.ui

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookApplication
import com.example.bookshelf.data.AppContainer
import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.data.DefaultAppContainer
import com.example.bookshelf.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.io.IOException

sealed interface BookUiState {
    data class Success(val books: List<Book>, ) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}


class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {

    var currentBook: Book? = null

    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set


    fun getBooks(query: String? = null) {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                val books = bookRepository.getBooks(query ?: "searchInput") ?: throw Exception()
                BookUiState.Success(books)
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: retrofit2.HttpException) {
                BookUiState.Error
            }
        }
    }

    var userInput: String by mutableStateOf("")
        private set

    fun changeUserGuess(input: String) {
        userInput = input
    }


    init {
        getBooks()
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val bookRepository = application.container.bookRepository
                BookViewModel(bookRepository = bookRepository)
            }
        }
    }


}