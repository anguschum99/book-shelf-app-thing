package com.example.bookshelf.data

import android.accounts.NetworkErrorException
import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookshelfApiService

interface BookRepository {
    suspend fun getBooks(q: String): List<Book>?
    suspend fun getBook(id: String): Book?
}

class NetworkBookRepository(
    private val bookshelfApiService: BookshelfApiService
) : BookRepository {

    /** Retrieves list from bookshelfApiService **/
    override suspend fun getBooks(q: String): List<Book>? {
        return try {
            val response = bookshelfApiService.getBooks(q)
            if (response.isSuccessful) response.body()?.items ?: emptyList()
            else throw NetworkErrorException(response.code().toString())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /** Retrieves individual book from bookshelfApiService **/
    override suspend fun getBook(id: String): Book? {
        return try {
            val response = bookshelfApiService.getBook(id)
            if (response.isSuccessful) response.body()
            else throw NetworkErrorException(response.code().toString())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}