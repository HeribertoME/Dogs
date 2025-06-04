package com.heriberto.dogswelove

import com.heriberto.dogswelove.data.datasource.remote.RemoteDataSource
import com.heriberto.dogswelove.data.datasource.remote.RemoteDataSourceImpl
import com.heriberto.dogswelove.data.datasource.remote.network.DogApiService
import com.heriberto.dogswelove.data.datasource.remote.network.responses.DogResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest {

    private lateinit var apiService: DogApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        apiService = mock()
        remoteDataSource = RemoteDataSourceImpl(apiService)
    }

    @Test
    fun `getDogs returns list of dogs when API call is successful`() = runTest {
        // Given
        val dogList = listOf(
            DogResponse("Chief", "Desc", 5, "url")
        )
        val response = Response.success(dogList)

        whenever(apiService.getDogs()).thenReturn(response)

        // When
        val result = remoteDataSource.getDogs()

        // Then
        assertEquals(dogList, result)
        verify(apiService).getDogs()
    }

    @Test(expected = Exception::class)
    fun `getDogs throws exception when API call fails`() = runTest {
        // Given
        val errorResponse = Response.error<List<DogResponse>>(
            500,
            "".toResponseBody("application/json".toMediaTypeOrNull())
        )

        whenever(apiService.getDogs()).thenReturn(errorResponse)

        // When
        remoteDataSource.getDogs()

        // Then
        // Exception is expected
    }
}
