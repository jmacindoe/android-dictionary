package dev.macindoe.dictionary.features.word_list

import android.content.Context
import android.view.View
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
        chinese_tv.text = word.chinese
        english_tv.text = word.english
        pinyin_tv.text = word.pinyin
        if (favoritesCallback != null) {
            favorite_btn.visibility = View.VISIBLE
            favorite_btn.setOnClickListener {
                favoritesCallback.unfavoriteWord(word)
            }
        } else {
            favorite_btn.visibility = View.GONE
            favorite_btn.setOnClickListener(null)
        }
    }
}