package com.example.betterwayass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.app.ActionBar.LayoutParams
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betterwayass.adapter.MainAdapter
import com.example.betterwayass.databinding.ActivityMainBinding
import com.example.betterwayass.model.Category
import com.example.betterwayass.model.Contact
import kotlinx.android.synthetic.main.contact_view.view.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter(){item ->
        changeActivity(item)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title= "Categories"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup(binding)


    }

    private fun setup(binding: ActivityMainBinding){
        val recyclerView=binding.contactCategory
        val layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }


    private fun changeActivity(category: Category){
        val intent = Intent(this@MainActivity,ContactItem::class.java)
        intent.putExtra("catName",category.name)
        startActivity(intent)
    }



}


