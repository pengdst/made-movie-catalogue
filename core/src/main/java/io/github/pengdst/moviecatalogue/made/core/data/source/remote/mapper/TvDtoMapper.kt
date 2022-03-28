package io.github.pengdst.moviecatalogue.made.core.data.source.remote.mapper

import io.github.pengdst.moviecatalogue.made.core.data.source.local.room.model.TvShowEntity
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.TvShowDto

/**
 * Created on 5/25/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Suppress("unused")
object TvDtoMapper {

    fun TvShowDto.toEntity() = TvShowEntity(
        id = id ?: 0,
        title = name.toString(),
        backdropPath = backdropPath.toString(),
        posterPath = posterPath.toString(),
        releaseDate = firstAirDate.toString(),
        language = spokenLanguages?.map { it.englishName }.toString()
            .replace("[", "").replace("]", ""),
        genre = genres?.map { it.name }.toString().replace("[", "")
            .replace("]", ""),
        storyLine = overview.toString()
    )

    fun List<TvShowDto>.toEntity() = map {
        it.toEntity()
    }
}