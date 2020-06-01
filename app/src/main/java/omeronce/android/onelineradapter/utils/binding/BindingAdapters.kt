package omeronce.android.onelineradapter.utils.binding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import omeronce.android.onelineradapter.model.books.Book
import java.lang.Exception

@BindingAdapter("imageBook")
fun bindBook(view: ImageView, book: Book) {

    val placeholder = ColorDrawable(Color.rgb(book.placeholderColor.red, book.placeholderColor.green, book.placeholderColor.blue))
    //just load image to view (no need to consider ratio in our case)
    Picasso.get().load(book.url).placeholder(placeholder).fit().into(view, object : Callback {
        override fun onSuccess() {
            Log.i("BindingAdapters", "bindBook: success")
        }

        override fun onError(e: Exception?) {
            Log.i("BindingAdapters", "bindBook: failed, message- ${e?.message}")
        }
    })
}