package io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models

import com.google.gson.annotations.SerializedName
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.relations.GenreDto
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.relations.ProductionCompanyDto
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.relations.ProductionCountryDto
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.relations.SpokenLanguageDto

data class MovieDto(

    @field:SerializedName("original_language")
	val originalLanguage: String? = null,

    @field:SerializedName("imdb_id")
	val imdbId: String? = null,

    @field:SerializedName("video")
	val video: Boolean? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

    @field:SerializedName("revenue")
	val revenue: Int? = null,

    @field:SerializedName("genres")
	val genres: List<GenreDto>? = null,

    @field:SerializedName("popularity")
	val popularity: Double? = null,

    @field:SerializedName("production_countries")
	val productionCountries: List<ProductionCountryDto>? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("vote_count")
	val voteCount: Int? = null,

    @field:SerializedName("budget")
	val budget: Int? = null,

    @field:SerializedName("overview")
	val overview: String? = null,

    @field:SerializedName("original_title")
	val originalTitle: String? = null,

    @field:SerializedName("runtime")
	val runtime: Int? = null,

    @field:SerializedName("poster_path")
	val posterPath: String? = null,

    @field:SerializedName("spoken_languages")
	val spokenLanguages: List<SpokenLanguageDto>? = null,

    @field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompanyDto>? = null,

    @field:SerializedName("release_date")
	val releaseDate: String? = null,

    @field:SerializedName("vote_average")
	val voteAverage: Double? = null,

    @field:SerializedName("belongs_to_collection")
	val belongsToCollection: Any? = null,

    @field:SerializedName("tagline")
	val tagline: String? = null,

    @field:SerializedName("adult")
	val adult: Boolean? = null,

    @field:SerializedName("homepage")
	val homepage: String? = null,

    @field:SerializedName("status")
	val status: String? = null
)

