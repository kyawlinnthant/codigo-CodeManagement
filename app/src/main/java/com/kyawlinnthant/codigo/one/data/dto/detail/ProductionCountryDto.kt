package com.kyawlinnthant.codigo.one.data.dto.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1")
    val iso31661: String? = "",
    @SerialName("name")
    val name: String? = "",
)