package com.example.betterwayass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.app.ActionBar.LayoutParams
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
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
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title= "Contacts"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup(binding)
        setupAction()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_btn,menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addCat ->{
                if(!alertDialog.isShowing){
                    this.alertDialog.show()
                }
            }
        }
        return true
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

    private fun addCategory(category:Category){
        if(!adapter.addCategory(category)){
            Toast.makeText(this,"These Category Name already exist..",Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this,"New Category created.",Toast.LENGTH_SHORT).show()
    }

    private fun setupAction(){
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_category, null)
        builder.setView(view)
        this.alertDialog = builder.create()

        val textview = view.findViewById<EditText>(R.id.editText)
        val btn = view.findViewById<Button>(R.id.btn)

        btn.setOnClickListener{
            if(textview.text!=null){
                addCategory(Category(textview.text.toString()))
            }
            alertDialog.dismiss()
            textview.text.clear()
        }


        textview.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn.isEnabled =  textview.text.length>= 3
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

}


