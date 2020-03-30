package dev.macindoe.dictionary.features.search

import android.content.Context
import android.widget.FrameLayout
import dev.macindoe.dictionary.R
import dev.macindoe.dictionary.data.Word
import kotlinx.android.synthetic.main.view_search_result.view.*

class SearchResultView(context: Context): FrameLayout(context) {
    init {
        inflate(context, R.layout.view_search_result, this)
    }

    fun bind(word: Word) {
        zh_tv.text = word.zh
    }
}