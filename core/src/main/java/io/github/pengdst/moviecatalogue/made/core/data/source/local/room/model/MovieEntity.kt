package io.github.pengdst.moviecatalogue.made.core.data.source.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var language: String,
    var genre: String,
    var storyLine: String,
    var updatedAt: Long = Date().time
){

    var isFavourite: Boolean = false
    var createdAt: Long = Date().time

}
