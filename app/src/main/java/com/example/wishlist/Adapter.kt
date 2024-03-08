package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    var wishlist: MutableList<WishListModel>, // Use MutableList for easy item removal
    private val onDeleteListener: (position: Int) -> Unit // Listener for deletion
) : RecyclerView.Adapter<Adapter.WishViewHolder>() {

    inner class WishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemView)
        val itemScoreTextView: TextView = itemView.findViewById(R.id.scoreView)
        val itemURLTextView: TextView = itemView.findViewById(R.id.urlView)

        init {
            // Add long-press listener for deletion
            itemView.setOnLongClickListener {
                onDeleteListener(adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_item, parent, false)
        return WishViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }

    override fun onBindViewHolder(holder: WishViewHolder, position: Int) {
        val currentWish = wishlist[position]
        holder.itemNameTextView.text = currentWish.itemName
        holder.itemScoreTextView.text = currentWish.score.toString()
        holder.itemURLTextView.text = currentWish.url
    }

    // Method to update the dataset and notify the adapter
    fun updateWishlist(newWishlist: List<WishListModel>) {
        wishlist.clear()
        wishlist.addAll(newWishlist)
        notifyDataSetChanged()
    }
}
