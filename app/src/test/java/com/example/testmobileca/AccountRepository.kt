package com.example.testmobileca

import com.example.testmobileca.data.model.BanksResponse
import com.example.testmobileca.data.repository.imp.AccountRepositoryImp
import com.example.testmobileca.retrofit.APIClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class AccountRepositoryTest {
    private var mockWebServer = MockWebServer()

    @Test
    suspend fun `test getAllAccounts`() {
        // Create a mock APIClient
        val apiClient = mock(APIClient::class.java)



        // Create an instance of AccountRepositoryImp
        val repository = AccountRepositoryImp(apiClient)

        // Mock APICLIENT behavior
       /* val fakeResponse = listOf(BanksResponse(mockWebServer.))
        `when`(apiClient.getAllAccounts()).thenReturn(fakeResponse)
*/

        val result = repository.getAllAccounts()

    }
}