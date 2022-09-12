package io.gamehub.feature.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import dagger.hilt.android.AndroidEntryPoint
import io.gamehub.core.utils.extensions.observe
import io.gamehub.data.games.models.Game
import io.gamehub.feature.home.databinding.FragmentHomeBinding
import io.gamehub.feature.home.databinding.LiItemCarouselBinding

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val viewBinding: FragmentHomeBinding by viewBinding()

    private val adapter by lazy {
        ListDelegationAdapter(adapterDelegate())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carousel = viewBinding.popularGames

        carousel.adapter = adapter
        carousel.enableAutoScroll(viewLifecycleOwner.lifecycleScope)

        viewModel.state.observe(viewLifecycleOwner) {
            observeState(it)
        }
    }

    private fun observeState(state: HomeViewModel.State) {
        with(viewBinding) {
            defaultLayout.isVisible = state is HomeViewModel.State.Default
            progressLayout.isVisible = state is HomeViewModel.State.Loading

            if (state is HomeViewModel.State.Default) {
                adapter.items = state.popularGames
            }
        }
    }

    private fun adapterDelegate() = adapterDelegateViewBinding<Game, Game, LiItemCarouselBinding>(
        { layoutInflater, root -> LiItemCarouselBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            Glide
                .with(requireContext())
                .load(item.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.image)
        }
    }
}
