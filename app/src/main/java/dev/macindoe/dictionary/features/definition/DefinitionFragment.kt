package dev.macindoe.dictionary.features.definition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import dev.macindoe.dictionary.R
import kotlinx.android.synthetic.main.fragment_definition.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DefinitionFragment : Fragment() {
    private val args: DefinitionFragmentArgs by navArgs()
    private val viewModel: DefinitionViewModel by viewModel { parametersOf(args.wordId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_definition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favorite_btn.setOnClickListener {
            viewModel.toggleFavorite()
        }

        viewModel.word.observe(viewLifecycleOwner) { word ->
            definition_tv.text = word.english
            val favoriteStringId = if (word.isFavorite) R.string.action_unfavorite else R.string.action_favorite
            favorite_btn.setText(favoriteStringId)
        }
    }
}
