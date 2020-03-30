package dev.macindoe.dictionary.features.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import dev.macindoe.dictionary.R
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        query_et.addTextChangedListener {  text ->
            viewModel.updateSearchQuery(text.toString())
        }

        val adapter = SearchAdapter()
        result_list.adapter = adapter

        viewModel.searchResults().observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }
    }
}
