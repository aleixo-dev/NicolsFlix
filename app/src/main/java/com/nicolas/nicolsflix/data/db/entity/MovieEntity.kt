package com.nicolas.nicolsflix.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicolas.nicolsflix.data.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
        @field:PrimaryKey(autoGenerate = true) val id: Int?,
        @field:ColumnInfo(name = "title") val title: String?,
        @field:ColumnInfo(name = "poster") val poster: String?,
        @field:ColumnInfo(name = "posterDetails") val posterDetails: String?,
        @field:ColumnInfo(name = "description") val description: String?,
        @field:ColumnInfo(name = "rating") val rating: String?,
        @field:ColumnInfo(name = "date") val date: String?
)

fun toMovies(listMovies: List<MovieEntity>): ArrayList<Movie> {
    val mylistFavorite = ArrayList<Movie>()
    for (myList in listMovies) {
        val favorite = Movie(
                myList.id,
                myList.title,
                myList.poster,
                myList.posterDetails,
                myList.description,
                myList.rating,
                myList.date, )
        mylistFavorite.add(favorite)
    }
    return mylistFavorite
}