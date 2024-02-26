package com.kyawlinnthant.codigo.one.data.database

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.kyawlinnthant.codigo.one.data.database.dao.DetailDao
import com.kyawlinnthant.codigo.one.data.database.db.MovieDatabase
import com.kyawlinnthant.codigo.one.data.database.entity.DetailEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.inject.Inject


@HiltAndroidTest
class DetailDaoTest {

    @get:Rule
    val testRule = HiltAndroidRule(this)

    private lateinit var dao: DetailDao

    @Inject
    lateinit var db: MovieDatabase

    private val mockDetail = DetailEntity(
        id = 1,
        adult = false,
        backdropPath = "",
        budget = 0,
        homepage = "",
        imdbId = "",
        originalLanguage = "",
        originalTitle = "",
        overview = "",
        popularity = 0.0,
        posterPath = "",
        releaseDate = "",
        revenue = 0,
        runtime = 0,
        status = "",
        tagline = "",
        title = "",
        video = false,
        voteAverage = 0.0,
        voteCount = 0,
    )

    @BeforeEach
    fun setup() {
        testRule.inject()
        dao = db.detailDao()
    }

    @AfterEach
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    @DisplayName("Insert with same primary key will replace")
    fun insert_successfully() =
        runTest {
             val mockDetail = DetailEntity(
                id = 1,
                adult = false,
                backdropPath = "",
                budget = 0,
                homepage = "",
                imdbId = "",
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                releaseDate = "",
                revenue = 0,
                runtime = 0,
                status = "",
                tagline = "",
                title = "",
                video = false,
                voteAverage = 0.0,
                voteCount = 0,
            )
            val detail1 = mockDetail.copy(id = 100, title = "This is the first title")
            val detail2 = mockDetail.copy(id = 100, title = "This is the new title")

            dao.insertDetail(detail1)
            val oldData = dao.getDetail(100)
            assertThat(oldData.title).isEqualTo(detail1.title)
            dao.insertDetail(detail2)
            val newData = dao.getDetail(100)
            assertThat(newData.title).isEqualTo(detail2.title)
        }

}