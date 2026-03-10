package com.example.ejercicioclase.data

import androidx.annotation.DrawableRes

data class Mascot(
    val name : String,
    @DrawableRes val image : Int,
    val dailyRegister : Int,
)
