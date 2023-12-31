package com.example.testmobileca.data.repository.abs

import androidx.annotation.WorkerThread
import com.example.testmobileca.data.model.BanksResponse

interface AccountRepository {


    /**
     * get datalist from server
     * @return List<BanksResponse>
     */
    @WorkerThread
    suspend fun getAllAccounts(): List<BanksResponse>

}