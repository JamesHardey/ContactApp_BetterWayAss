package com.example.betterwayass.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.betterwayass.CategoryList
import com.example.betterwayass.ContactItem
import com.example.betterwayass.databinding.CategoryItemBinding
import com.example.betterwayass.model.Category

class MainAdapter(private val listener:(Category)->Unit):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val categoryList = CategoryList.getCategoryList()


    fun setupCategory(category: Category){
        this.categoryList.addCategory(category)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val category = categoryList.categories[position]
        holder.bind(category)
        holder.itemView.setOnClickListener{listener(category)}
    }

    override fun getItemCount(): Int {
        return categoryList.categories.size
    }


    inner class MainViewHolder(private val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category){
            binding.categoryName.text=category.name
        }

    }


}