package com.example.bookshelf.fake

import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.BookUiState
import com.example.bookshelf.ui.BookViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookViewModel_getBooks_verifyUiStateSuccess() =
        runTest {
            val bookViewModel = BookViewModel(
                bookRepository = FakeNetworkRepository()
            )
            assertEquals(
                BookUiState.Success(FakeDataSource.books),
                bookViewModel.bookUiState
            )
        }
}