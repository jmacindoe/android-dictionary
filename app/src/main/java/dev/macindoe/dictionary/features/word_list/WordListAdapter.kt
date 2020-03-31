package dev.macindoe.dictionary.features.word_list

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.macindoe.dictionary.data.Word
import dev.macindoe.dictionary.features.search.SearchFragmentDirections


/// Adapter for a list of words. If [favoritesCallback] is specified, a button to unfavorite words is shown.
class WordListAdapter(private val favoritesCallback: FavoritesCallback? = null) : ListAdapter<Word, WordListAdapter.ViewHolder>(
    SearchDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = WordListItemView(parent.context)
        val lp: RecyclerView.LayoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.setLayoutParams(lp);
        return ViewHolder(view, favoritesCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val view: WordListItemView,
        private val favoritesCallback: FavoritesCallback?
    ) : RecyclerView.ViewHolder(view) {
        fun bind(word: Word) {
            view.bind(word, favoritesCallback)
            view.setOnClickListener {
                navigateToDetails(word.id, view)
            }
        }

        private fun navigateToDetails(wordId: String, view: View) {
            val action =
                SearchFragmentDirections.actionSearchFragmentToDefinitionFragment(
                    wordId
                )
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

interface FavoritesCallback {
    fun favoriteWord(word: Word)
    fun unfavoriteWord(word: Word)
}