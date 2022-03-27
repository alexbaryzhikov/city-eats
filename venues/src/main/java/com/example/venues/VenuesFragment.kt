package com.example.venues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.venues.databinding.VenuesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

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
        venuesAdapter = VenuesAdapter(viewLifecycleOwner)
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
}
