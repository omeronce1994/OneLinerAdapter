package omeronce.android.onelineradapter.model.books


import com.google.gson.annotations.SerializedName

data class BooksResult(
    @SerializedName("data")
    val data: List<Book> = listOf()
)