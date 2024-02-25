package com.kyawlinnthant.codigo.one.data.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = PageKeyEntity.NAME
)
data class PageKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextPage: Int?,
    val prevPage: Int?,
    val currentPage: Int,
) {
    companion object {
        const val NAME = "page_key"
    }
}
