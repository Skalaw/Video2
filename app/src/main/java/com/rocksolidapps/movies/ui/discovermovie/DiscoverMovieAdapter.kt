package com.rocksolidapps.movies.ui.discovermovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rocksolidapps.core.domain.model.MovieSimple
import com.rocksolidapps.movies.databinding.ItemDiscoverMovieBinding

class DiscoverMovieAdapter(private val onClickListener: (item: MovieSimple) -> Unit) : ListAdapter<MovieSimple, DiscoverViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding = ItemDiscoverMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) = holder.bind(getItem(position))
}

class DiscoverViewHolder(
    private val binding: ItemDiscoverMovieBinding,
    private val onClickListener: (item: MovieSimple) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieSimple) {
        binding.clickListener = View.OnClickListener { onClickListener.invoke(item) }
        binding.item = item
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieSimple>() {
    override fun areItemsTheSame(oldItem: MovieSimple, newItem: MovieSimple): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: MovieSimple, newItem: MovieSimple): Boolean = oldItem == newItem
}