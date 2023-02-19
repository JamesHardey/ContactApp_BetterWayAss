package com.example.betterwayass.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.betterwayass.CategoryList
import com.example.betterwayass.databinding.ContactViewBinding
import com.example.betterwayass.model.Category
import com.example.betterwayass.model.Contact

class ContactAdapter(category: Category, private val listener:(Contact)->Unit): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val categoryList = CategoryList.getCategoryList()

    private val contacts = categoryList.categories[categoryList.categories.indexOf(category)].contacts

    fun setupContact(contact: Contact){
        this.contacts.add(contact)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindItem(contact)
        holder.itemView.setOnClickListener{listener(contact)}
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ContactViewHolder(private val binding:ContactViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(contact: Contact){
            binding.name.text = contact.name
            binding.number.text = contact.number
            binding.imageButton.text = contact.name[0].toString().toUpperCase()
        }
    }


}
