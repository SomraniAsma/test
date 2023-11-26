package com.example.testmobileca.data.repository.imp

import androidx.annotation.WorkerThread
import com.example.testmobileca.base.BaseRepository
import com.example.testmobileca.data.model.BanksResponse
import com.example.testmobileca.data.repository.abs.AccountRepository
import com.example.testmobileca.retrofit.APIClient
import javax.inject.Inject

class AccountRepositoryImp @Inject constructor(
    apiClient: APIClient,
) : BaseRepository(apiClient), AccountRepository {



    @WorkerThread
    override suspend fun getAllAccounts(): List<BanksResponse> {
        return apiClient.getAllAccounts()
    }

}