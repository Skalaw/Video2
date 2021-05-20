package com.rocksolidapps.movies.ui.discovermovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rocksolidapps.core.domain.model.DiscoverMovieUi
import com.rocksolidapps.movies.databinding.ItemDiscoverMovieBinding

class DiscoverMovieAdapter(private val onClickListener: (item: DiscoverMovieUi) -> Unit) : ListAdapter<DiscoverMovieUi, DiscoverViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding = ItemDiscoverMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) = holder.bind(getItem(position))
}

class DiscoverViewHolder(
    private val binding: ItemDiscoverMovieBinding,
    private val onClickListener: (item: DiscoverMovieUi) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DiscoverMovieUi) {
        binding.clickListener = View.OnClickListener { onClickListener.invoke(item) }
        binding.item = item
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<DiscoverMovieUi>() {
    override fun areItemsTheSame(oldItem: DiscoverMovieUi, newItem: DiscoverMovieUi): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: DiscoverMovieUi, newItem: DiscoverMovieUi): Boolean = oldItem == newItem
}