package dev.macindoe.dictionary.features.word

import android.content.Context
import android.widget.FrameLayout
import dev.macindoe.dictionary.R
import dev.macindoe.dictionary.data.Word
import kotlinx.android.synthetic.main.view_search_result.view.*

class WordListItemView(context: Context): FrameLayout(context) {
    init {
        inflate(context, R.layout.view_search_result, this)
    }

    /// If [favoritesCallback] is specified, a button to unfavorite words is shown
    fun bind(word: Word, favoritesCallback: FavoritesCallback?) {
        zh_tv.text = word.zh
        if (favoritesCallback != null) {
            zh_tv.setOnClickListener {
                favoritesCallback.unfavoriteWord(word)
            }
        }
    }
}