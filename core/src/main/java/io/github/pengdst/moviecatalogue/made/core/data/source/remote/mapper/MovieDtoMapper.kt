package io.github.pengdst.moviecatalogue.made.core.data.source.remote.mapper

import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.model.MovieEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.MovieDto

/**
 * Created on 5/25/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
@Suppress("unused")
object MovieDtoMapper {

    fun MovieDto.toEntity() = MovieEntity(
        id = id ?: 0,
        title = title.toString(),
        backdropPath = backdropPath.toString(),
        posterPath = posterPath.toString(),
        releaseDate = releaseDate.toString(),
        language = spokenLanguages?.map { it.englishName }.toString()
            .replace("[", "").replace("]", ""),
        genre = genres?.map { it.name }.toString().replace("[", "")
            .replace("]", ""),
        storyLine = overview.toString()
    )

    fun List<MovieDto>.toEntity() = map {
        it.toEntity()
    }

}