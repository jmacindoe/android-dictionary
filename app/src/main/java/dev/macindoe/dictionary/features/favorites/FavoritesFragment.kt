package dev.macindoe.dictionary.features.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import dev.macindoe.dictionary.R
import dev.macindoe.dictionary.features.word.FavoritesCallback
import dev.macindoe.dictionary.features.word.WordListAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {
    private val viewModel: FavoritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WordListAdapter(viewModel)
        favorites_list.adapter = adapter

        viewModel.favorites().observe(viewLifecycleOwner) { data ->
            if (data.isEmpty()) {
                showEmptyState()
            } else {
                showResults()
                adapter.submitList(data)
            }
        }
    }

    private fun showEmptyState() {
        empty_v.visibility = View.VISIBLE
        favorites_list.visibility = View.GONE
    }

    private fun showResults() {
        empty_v.visibility = View.GONE
        favorites_list.visibility = View.VISIBLE
    }
}
