package com.example.ejercicioclase.data

import androidx.annotation.DrawableRes

data class Product(@param: DrawableRes val image : Int, val name : String, val price : String, val categoria : Categoria, val id : Int, val description: String = "")
