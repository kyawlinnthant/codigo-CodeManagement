package com.kyawlinnthant.codigo.one.data.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Retrofit

class MovieApiTest {

    private lateinit var service: MovieApi
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    private val factory = json.asConverterFactory("application/json".toMediaType())

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(factory)
            .build().create(MovieApi::class.java)
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val mockResponse = MockResponse()
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8)),
        )
    }

    @Test
    fun `Malformed response throws exception`() = runTest {
        enqueueResponse("malformed.json")
        assertThrows<Exception> {
            service.getDetail(
                id = 1
            )
        }
    }

    @Test
    fun `Fetch upcoming or popular with success response, successfully encoded`() = runTest {
        enqueueResponse("success.json")
        val response = service.getPopular(
            size = 50,
            page = 12
        )
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path).isEqualTo("/" + MovieApi.POPULAR + "?limit=50&page=12&api_key=9ea7ba427112520832f5da2af3a47bd4")
        assertThat(response.page).isEqualTo(12)
        assertThat(response.results).isNotEmpty()

    }

    @Test
    fun `Fetch detail with success response, successfully encoded`() = runTest {
        enqueueResponse("detail.json")
        val response = service.getDetail(
            id = 1072790
        )
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path).isEqualTo("/" + "movie/1072790?api_key=9ea7ba427112520832f5da2af3a47bd4")
        assertThat(response.body()!!.id).isEqualTo(1072790)
    }
}
