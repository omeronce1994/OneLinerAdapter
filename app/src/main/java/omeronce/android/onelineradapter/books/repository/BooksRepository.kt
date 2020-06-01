package omeronce.android.onelineradapter.books.repository

import androidx.lifecycle.LiveData
import omeronce.android.onelineradapter.model.Result
import omeronce.android.onelineradapter.model.books.Book

interface BooksRepository {

    fun observeBooks(): LiveData<Result<List<Book>>>
    suspend fun getBooks()
}