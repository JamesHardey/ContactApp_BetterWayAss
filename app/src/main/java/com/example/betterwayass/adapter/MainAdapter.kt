package com.example.betterwayass.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.betterwayass.CategoryList
import com.example.betterwayass.ContactItem
import com.example.betterwayass.databinding.CategoryItemBinding
import com.example.betterwayass.model.Category

class MainAdapter(private val listener:(Category)->Unit):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val categoryList = CategoryList.getCategoryList()

    private val colors = listOf<String>(
        "#000080","#FF00FF",
        "#FF0000","#008080",
        "#008000", "#0000FF"
    )
    private var num = 0


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
        if(num==colors.size){
            num = 0
        }
        holder.bind(category,colors[num])
        num++
        holder.itemView.setOnClickListener{listener(category)}
    }

    override fun getItemCount(): Int {
        return categoryList.categories.size
    }

    fun addCategory(category:Category):Boolean{
        return if(!categoryList.addCategory(category)){
            false
        } else{

            notifyDataSetChanged()
            true
        }

    }


    inner class MainViewHolder(private val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category,color:String){
            binding.categoryName.text=category.name
            val namme = category.name
            binding.imagee.text = namme[0].toString()
            binding.cardview.setBackgroundColor(Color.parseColor(color))
        }

    }


}