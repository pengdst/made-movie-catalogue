package io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.TvShowDto

data class TvResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<TvShowDto>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)