package com.example.venues.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.common.imageloader.ImageLoader
import com.example.common.util.launchAndRepeatWithViewLifecycle
import com.example.venues.R
import com.example.venues.databinding.VenuesFragmentBinding
import com.example.venues.model.VenuesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class VenuesFragment : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val venuesViewModel: VenuesViewModel by viewModels()

    private lateinit var binding: VenuesFragmentBinding
    private lateinit var venuesAdapter: VenuesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VenuesFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = venuesViewModel
        }

        val loadingAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_cw)
        binding.compassImageView.startAnimation(loadingAnimation)
        venuesViewModel.loading
            .takeWhile { it }
            .onCompletion {
                binding.compassImageView.clearAnimation()
                loadingAnimation.cancel()
                loadingAnimation.reset()
            }
            .launchIn(lifecycleScope)

        venuesAdapter = VenuesAdapter(this, venuesViewModel, imageLoader)
        binding.venuesRecyclerView.apply {
            adapter = venuesAdapter
            (itemAnimator as DefaultItemAnimator).run {
                supportsChangeAnimations = false
                addDuration = 160L
                moveDuration = 160L
                changeDuration = 160L
                removeDuration = 120L
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchAndRepeatWithViewLifecycle(Lifecycle.State.STARTED) {
            launch {
                venuesViewModel.venuesState.collect(this@VenuesFragment::updateVenues)
            }
        }
    }

    private fun updateVenues(venues: VenuesState) {
        venuesAdapter.submitList(venues.items)
    }
}
