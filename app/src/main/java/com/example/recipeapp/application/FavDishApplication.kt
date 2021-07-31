package com.example.recipeapp.application

import android.app.Application
import com.example.recipeapp.model.database.FavDishRepository
import com.example.recipeapp.model.database.FavDishRoomDatabase

class FavDishApplication : Application() {
    private val database by lazy { FavDishRoomDatabase.getDatabase(this@FavDishApplication) }
    val repository by lazy { FavDishRepository(database.favDishDao()) }
}