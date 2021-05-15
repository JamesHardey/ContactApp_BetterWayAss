package com.example.betterwayass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betterwayass.adapter.MainAdapter
import com.example.betterwayass.databinding.ActivityMainBinding
import com.example.betterwayass.model.Category
import com.example.betterwayass.model.Contact
import java.util.*

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter(){item ->
        changeActivity(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDatas()
        setup(binding)

    }

    private fun setup(binding: ActivityMainBinding){
        val recyclerView=binding.contactCategory
        val layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.adapter


    }

    private fun setDatas(){
        adapter.setupCategory(Category("Friends"))
        adapter.setupCategory(Category("Family"))
        adapter.setupCategory(Category("Teacher"))
        adapter.setupCategory(Category("Pastors"))
        adapter.setupCategory(Category("Neighbours"))
    }

    private fun changeActivity(category: Category){
        val intent = Intent(this@MainActivity,ContactItem::class.java)
        startActivity(intent)
    }

}


