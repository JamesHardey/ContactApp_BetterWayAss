package com.example.betterwayass

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.betterwayass.adapter.ContactAdapter
import com.example.betterwayass.databinding.ContactItemBinding
import com.example.betterwayass.model.Category
import com.example.betterwayass.model.Contact
import kotlinx.android.synthetic.main.category_item.*

class ContactItem : AppCompatActivity() {
    private val requestCall = 1
    private var saveNumber:Contact? = null
    private lateinit var binding: ContactItemBinding
    private lateinit var adapter: ContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle? =intent.extras
        val name = bundle?.getString("catName")
        title = name
        val category = lookUpCategory(name)
        adapter = ContactAdapter(category){
            callContact(it)
        }
        binding = ContactItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupData(binding)
    }

    private fun callContact(contact: Contact) {
        val number = contact.number

        if(number.trim {it <= ' '}.isNotEmpty()){
            if(ContextCompat.checkSelfPermission(
                    this@ContactItem,
                    Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED)
            {
                saveNumber = contact
                ActivityCompat.requestPermissions(
                    this@ContactItem,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    requestCall
                )
            }
            else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == requestCall) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveNumber?.let{ it -> callContact(it) }
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun lookUpCategory(name:String?):Category{
        val categoryList = CategoryList.getCategoryList()
        return categoryList.getCategoryByName(name)
    }


    private fun setupData(binding: ContactItemBinding?) {
        binding?.contactRV?.adapter = adapter

        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact, null)
        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.contactName)
        val number = view.findViewById<TextView>(R.id.phoneNumber)
        val btn = view.findViewById<Button>(R.id.saveBtn)

        val alertDialog = builder.create()

        btn.setOnClickListener {
            val contact = Contact(name.text.toString(), number.text.toString())
            //val contacts = mutableListOf<Contact>(contact)
            adapter.setupContact(contact)
            alertDialog.dismiss()
            number.text = ""
            name.text = ""
            name.clearFocus()
            number.clearFocus()
        }

        number?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn.isEnabled = name.text.isNotEmpty() && number.text.length == 11

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding?.addContact?.setOnClickListener {
            alertDialog.show()
        }

    }
}
