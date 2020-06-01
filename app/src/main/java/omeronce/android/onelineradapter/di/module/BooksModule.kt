package omeronce.android.onelineradapter.di.module

import omeronce.android.onelineradapter.books.datasource.BooksDataSource
import omeronce.android.onelineradapter.books.datasource.RemoteBookDataSource
import omeronce.android.onelineradapter.books.repository.BooksRepository
import omeronce.android.onelineradapter.books.repository.BooksRepositoryImpl
import omeronce.android.onelineradapter.books.viewmodel.BooksViewModel
import org.koin.dsl.module

val bookModule = module {
    factory<BooksDataSource> { RemoteBookDataSource() }
    factory<BooksRepository> { BooksRepositoryImpl(get()) }
    factory { BooksViewModel(get()) }
}