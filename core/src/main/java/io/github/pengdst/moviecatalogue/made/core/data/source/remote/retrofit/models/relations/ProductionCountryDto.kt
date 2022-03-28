package io.github.pengdst.moviecatalogue.made.core.data.source.remote.retrofit.models.relations

import com.google.gson.annotations.SerializedName

data class ProductionCountryDto(

	@field:SerializedName("iso_3166_1")
	val iso31661: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)