package com.example.materialtest.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.materialtest.FruitActivity
import com.example.materialtest.R
import com.example.materialtest.entity.Fruit

class FruitAdapter(val context: Context, val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fruitImage: ImageView = itemView.findViewById(R.id.fruitImage)
        val fruitName: TextView = itemView.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList.get(position)
            val intent = Intent(context,FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME,fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.imageId)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList.get(position)
        holder.fruitName.text = fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)


    }

    override fun getItemCount(): Int {
        return fruitList.size
    }
}