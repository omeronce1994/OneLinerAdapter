package omeronce.android.onelineradapter.books.datasource

import omeronce.android.onelineradapter.model.Result
import omeronce.android.onelineradapter.model.books.Book
import omeronce.android.onelineradapter.model.books.BooksResult

interface BooksDataSource {
    suspend fun getBooks(): Result<List<Book>>
}