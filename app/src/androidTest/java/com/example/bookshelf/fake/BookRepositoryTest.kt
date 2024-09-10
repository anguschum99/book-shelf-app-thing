package com.example.bookshelf.fake

import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.data.NetworkBookRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class BookRepositoryTest {
    @Test
    fun bookRepository_getBooks_verifyBookList() =
        runTest {
            val repository = NetworkBookRepository(FakeBookshelfApiService())
            assertEquals(FakeDataSource.books, repository.getBooks("searchInput"))
        }
}