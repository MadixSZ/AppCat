package com.example.catapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catapp.databinding.ItemCatBinding
import com.example.catapp.model.CatImage

class CatAdapter(private var cats: List<CatImage>) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    inner class CatViewHolder(val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.binding.ivCat.load(cats[position].imageUrl) {
            crossfade(true)
            error(android.R.drawable.stat_notify_error)
        }
    }

    override fun getItemCount() = cats.size

    fun updateList(newCats: List<CatImage>) {
        cats = newCats
        notifyDataSetChanged()
    }
}