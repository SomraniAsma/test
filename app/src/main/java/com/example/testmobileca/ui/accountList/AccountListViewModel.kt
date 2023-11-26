package com.example.testmobileca.ui.accountList

import android.app.Application
import android.content.ContentValues
import androidx.lifecycle.viewModelScope
import com.example.testmobileca.base.BaseViewModel
import com.example.testmobileca.data.repository.abs.AccountRepository
import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.utils.Logger
import com.example.testmobileca.global.utils.tryCatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AccountListViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val accountRepository: AccountRepository
) : BaseViewModel(application, schedulerProvider) {




    init { }

    fun loadInitial() {
        viewModelScope.launch {
            tryCatch({
                val response = withContext(schedulerProvider.dispatchersIO()) {
                    accountRepository.getAllAccounts()
                }
                Logger.d(ContentValues.TAG, response.toString())

            }, {
                Logger.d(ContentValues.TAG, it.toString())
            })
        }
    }


}
