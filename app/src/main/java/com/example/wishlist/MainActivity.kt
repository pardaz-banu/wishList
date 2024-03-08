package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity()
{
    private lateinit var itemNameEditText: EditText
    private lateinit var scoreEditText: EditText
    private lateinit var urlEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemNameEditText = findViewById(R.id.itemName)
        scoreEditText = findViewById(R.id.score)
        urlEditText = findViewById(R.id.url)
        submitButton = findViewById(R.id.button)
        recyclerView = findViewById(R.id.recyclerview)

        val wishlistItems = mutableListOf<WishListModel>()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = Adapter(wishlistItems) { position ->
            wishlistItems.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        //adapter = RecyclerView.Adapter(wishlistItems)
        recyclerView.adapter = adapter

        submitButton.setOnClickListener {
            val itemName = itemNameEditText.text.toString()
            val scoreText = scoreEditText.text.toString()
            val url = urlEditText.text.toString()

            if (itemName.isNotBlank() && scoreText.isNotBlank() && url.isNotBlank()) {
                // Convert scoreText to Double
                val score = scoreText.toDouble()

                // Create a new WishListModel object
                val newWish = WishListModel(itemName, score, url)

                val updatedWishlist = adapter.wishlist.toMutableList()
                updatedWishlist.add(newWish)

                adapter.updateWishlist(updatedWishlist)

                // Add the new item to the RecyclerView
                //wishlistItems.add(newWish)
                //adapter.notifyDataSetChanged()

                // Clear input fields after adding an item
                itemNameEditText.text.clear()
                scoreEditText.text.clear()
                urlEditText.text.clear()
            }
        }

    }
}