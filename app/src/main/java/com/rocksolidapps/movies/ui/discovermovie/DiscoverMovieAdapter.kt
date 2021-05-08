package com.rocksolidapps.movies.ui.discovermovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rocksolidapps.core.domain.model.MovieSimple
import com.rocksolidapps.movies.databinding.ItemDiscoverMovieBinding

class DiscoverMovieAdapter : ListAdapter<MovieSimple, DiscoverViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding = ItemDiscoverMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) = holder.bind(getItem(position))
}

class DiscoverViewHolder(private val binding: ItemDiscoverMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieSimple) {
        binding.item = item
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieSimple>() {
    override fun areItemsTheSame(oldItem: MovieSimple, newItem: MovieSimple): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: MovieSimple, newItem: MovieSimple): Boolean = oldItem == newItem
}