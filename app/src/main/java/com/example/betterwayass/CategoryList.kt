package com.example.betterwayass

import com.example.betterwayass.model.Category
import com.example.betterwayass.model.Contact

class CategoryList {
    val categories = mutableListOf<Category>()

    init{
        categories.add(Category("Friends"))
        categories.add(Category("Family"))
        categories.add(Category("Tutors"))
        categories.add(Category("Neighbours"))
        categories.add(Category("Church Members"))
    }

    companion object {

        private var INSTANCE:CategoryList? =null


        fun getCategoryList():CategoryList{
            if(INSTANCE == null){
                INSTANCE = CategoryList()
            }
            return INSTANCE as CategoryList
        }


    }

    fun addCategory(category:Category){
        categories.add(category)
    }

    fun addToList(category: Category,contact: Contact){
        categories[categories.indexOf(category)].contacts.add(contact)
    }

    fun contacts(category:Category):List<Contact>?{
        return if(categories.contains(category)){
            categories[categories.indexOf(category)].contacts
        } else{
            null
        }
    }

    fun getCategoryByName(name:String?): Category {
        var cat:Category? = null
        categories.forEach{category ->
            if(category.name==name) cat = category
        }
        return cat!!
    }


    

}