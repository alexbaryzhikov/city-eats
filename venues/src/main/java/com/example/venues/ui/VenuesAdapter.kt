package com.example.venues.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.imageloader.ImageLoader
import com.example.venues.R
import com.example.venues.databinding.VenueItemBinding
import com.example.venues.model.Venue

class VenuesAdapter(
    private val fragment: VenuesFragment,
    private val viewModel: VenuesViewModel,
    private val imageLoader: ImageLoader
) : ListAdapter<Venue, VenueViewHolder>(VenueDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val binding = VenueItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .apply { lifecycleOwner = fragment }
        return VenueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val venue = getItem(position)
        if (venue.iconUrl != null) {
            imageLoader.load(
                imageView = holder.binding.leadIcon,
                url = venue.iconUrl,
                placeholder = R.drawable.ic_local_cafe,
                fragment = fragment
            )
        } else {
            holder.binding.leadIcon.setImageResource(R.drawable.ic_local_cafe)
        }
        holder.binding.venue = venue
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