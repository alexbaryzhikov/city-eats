package com.example.venues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.venues.databinding.VenueItemBinding

class VenuesAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<VenueState, VenueViewHolder>(VenueDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val binding = VenueItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .apply { lifecycleOwner = this@VenuesAdapter.lifecycleOwner }
        return VenueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        holder.binding.venue = getItem(position)
        holder.binding.executePendingBindings()
    }
}

class VenueViewHolder(
    val binding: VenueItemBinding
) : RecyclerView.ViewHolder(binding.root)

object VenueDiff : DiffUtil.ItemCallback<VenueState>() {
    override fun areItemsTheSame(oldItem: VenueState, newItem: VenueState): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: VenueState, newItem: VenueState): Boolean {
        return oldItem == newItem
    }
}