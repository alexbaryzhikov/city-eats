package com.example.venues.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.common.util.launchAndRepeatWithViewLifecycle
import com.example.venues.databinding.VenuesFragmentBinding
import com.example.venues.model.VenuesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VenuesFragment : Fragment() {

    private val venuesViewModel: VenuesViewModel by viewModels()

    private lateinit var binding: VenuesFragmentBinding
    private lateinit var venuesRecyclerView: RecyclerView
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
        venuesRecyclerView = binding.venuesRecyclerView
        venuesAdapter = VenuesAdapter(viewLifecycleOwner, venuesViewModel)
        venuesRecyclerView.apply {
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
