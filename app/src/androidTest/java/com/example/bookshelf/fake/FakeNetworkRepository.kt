package com.example.bookshelf.fake

import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.BookUiState
import com.example.bookshelf.ui.BookViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FakeNetworkRepository : BookRepository {
    override suspend fun getBooks(q: String): List<Book>? {
        return FakeDataSource.books
    }
    override suspend fun getBook(id: String): Book? {
        return FakeDataSource.books[0]
    }

}