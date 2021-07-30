package com.example.recipeapp.model.database

import com.example.recipeapp.model.entities.FavDish

class FavDishRepository(private val favDishDao: FavDishDao) {

    suspend fun insertFavDishData(favDish: FavDish) {
        favDishDao.insertFavDishDetails(favDish)
    }
}