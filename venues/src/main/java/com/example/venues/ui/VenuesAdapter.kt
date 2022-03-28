package com.example.venues.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.venues.databinding.VenueItemBinding
import com.example.venues.model.Venue

class VenuesAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: VenuesViewModel
) : ListAdapter<Venue, VenueViewHolder>(VenueDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val binding = VenueItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .apply { lifecycleOwner = this@VenuesAdapter.lifecycleOwner }
        return VenueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        holder.binding.venue = getItem(position)
        holder.binding.viewModel = viewModel
        holder.binding.executePendingBindings()
    }
}

class VenueViewHolder(
    val binding: VenueItemBinding
) : RecyclerView.ViewHolder(binding.root)

object VenueDiff : DiffUtil.ItemCallback<Venue>() {
    override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return oldItem == newItem
    }
}