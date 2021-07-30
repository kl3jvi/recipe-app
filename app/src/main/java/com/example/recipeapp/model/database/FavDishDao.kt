package com.example.recipeapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.recipeapp.model.entities.FavDish

@Dao
interface FavDishDao {

    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish){

    }

}