package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.Books
import com.example.bookshelf.network.BookshelfApiService
import retrofit2.Response
import retrofit2.Response.success
import retrofit2.http.Query

class FakeBookshelfApiService : BookshelfApiService {
    override suspend fun getBooks(q: String): Response<Books> {
        return Response.success(
            Books(
                items = FakeDataSource.books,
                kind = "book#volumes",
                totalItems = 1
            )
        )
    }
    override suspend fun getBook(id: String): Response<Book> {
        return success(FakeDataSource.books[0])
    }

}