package omeronce.android.onelineradapter.books.datasource

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import omeronce.android.onelineradapter.MyApplication
import omeronce.android.onelineradapter.model.Result
import omeronce.android.onelineradapter.model.books.Book
import omeronce.android.onelineradapter.model.books.BooksResult
import org.koin.core.KoinComponent
import org.koin.core.get
import java.lang.Exception

class RemoteBookDataSource(): BooksDataSource, KoinComponent {
    override suspend fun getBooks(): Result<List<Book>> {
        val application = get<Context>()
        val file_name = "books.json"
        try {
            val json_string = application.assets.open(file_name).bufferedReader().use{
                it.readText()
            }
            val gson = Gson()
            val booksResult = gson.fromJson<BooksResult>(json_string, BooksResult::class.java)
            return Result.Success(booksResult.data)
        }
        catch (exception: Exception) {
            exception.printStackTrace()
            return Result.Error(exception)
        }
    }
}