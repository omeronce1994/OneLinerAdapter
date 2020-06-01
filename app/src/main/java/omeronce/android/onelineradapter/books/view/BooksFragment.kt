package omeronce.android.onelineradapter.books.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import omeronce.android.onelineradapter.BR
import omeronce.android.onelineradapter.R
import omeronce.android.onelineradapter.adapters.OneLinerAdapter
import omeronce.android.onelineradapter.books.viewmodel.BooksViewModel
import omeronce.android.onelineradapter.databinding.FragmentBooksBinding
import omeronce.android.onelineradapter.model.Result
import omeronce.android.onelineradapter.model.books.Book
import omeronce.android.onelineradapter.view.base.BaseFragment
import omeronce.android.onelineradapter.adapters.OneLinerListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class BooksFragment: BaseFragment() {

    companion object {
        fun newInstance(): BooksFragment {
            val bundle = Bundle()
            val fragment = BooksFragment().apply { arguments = bundle }
            return fragment
        }
    }

    private val booksViewModel: BooksViewModel by viewModel()
    private lateinit var binding: FragmentBooksBinding
    private val adapter by lazy {
        OneLinerAdapter.Builder<Book>().withLayout(R.layout.item_book).toVariableId(BR.book).withItemClickListener {
            Log.i("BooksFragment", "onItemClicked: $it")
        }.setDiffUtil(Book.diffCallback).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    private fun observeData() {
        booksViewModel.books.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> {
                    adapter.submitList(it.value)
                }
            }
        })
    }

    private fun initView() {
        initRecycler()
    }

    private fun initRecycler() {
        binding.rvBooks.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvBooks.adapter = adapter
    }
}