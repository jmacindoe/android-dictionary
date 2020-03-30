package dev.macindoe.dictionary.features.search

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.macindoe.dictionary.data.Word


class SearchAdapter : ListAdapter<Word, SearchAdapter.ViewHolder>(SearchDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SearchResultView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val view: SearchResultView
    ) : RecyclerView.ViewHolder(view) {
        fun bind(word: Word) {
            view.bind(word)
            view.setOnClickListener {
                navigateToDetails(word.id, view)
            }
        }

        private fun navigateToDetails(wordId: String, view: View) {
            val action = SearchFragmentDirections.actionSearchFragmentToDefinitionFragment(wordId)
            view.findNavController().navigate(action)
        }
    }
}

private class SearchDiffCallback : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}
