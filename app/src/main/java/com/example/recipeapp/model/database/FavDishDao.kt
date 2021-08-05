package com.example.recipeapp.model.database

import androidx.room.*
import com.example.recipeapp.model.entities.FavDish
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDishDao {

    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE ORDER BY ID")
    fun getAllDishesList(): Flow<List<FavDish>>

    @Update
    suspend fun updateFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE WHERE favorite_dish = 1")
    fun getFavoriteDishesList(): Flow<List<FavDish>>

    @Delete
    suspend fun deleteFavDish(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE WHERE type=:filterType")
    fun getFilteredDishesList(filterType: String): Flow<List<FavDish>>


}