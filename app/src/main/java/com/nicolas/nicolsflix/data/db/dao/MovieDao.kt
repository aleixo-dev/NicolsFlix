package com.nicolas.nicolsflix.data.db.dao

import androidx.room.*
import com.nicolas.nicolsflix.data.db.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

}