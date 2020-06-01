package omeronce.android.onelineradapter.books.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import omeronce.android.onelineradapter.books.datasource.BooksDataSource
import omeronce.android.onelineradapter.model.Result
import omeronce.android.onelineradapter.model.books.Book

class BooksRepositoryImpl(val dataSource: BooksDataSource): BooksRepository {

    private val books: MutableLiveData<Result<List<Book>>> by lazy { MutableLiveData<Result<List<Book>>>() }

    override suspend fun getBooks() {
        val result = dataSource.getBooks()
        books.postValue(result)
    }

    override fun observeBooks(): LiveData<Result<List<Book>>> = books
}