package com.example.testmobileca.ui.accountList

import android.app.Application
import android.content.ContentValues
import androidx.lifecycle.viewModelScope
import com.example.testmobileca.base.BaseViewModel
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.BanksResponse
import com.example.testmobileca.data.model.Category
import com.example.testmobileca.data.repository.abs.AccountRepository
import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.utils.Logger
import com.example.testmobileca.global.utils.tryCatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AccountListViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val accountRepository: AccountRepository
) : BaseViewModel(application, schedulerProvider) {

    private val _accountList: MutableStateFlow<List<BanksResponse>> = MutableStateFlow(emptyList())
    val accountList: StateFlow<List<BanksResponse>> get() = _accountList

    private val _caList: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val caList: StateFlow<List<Category>> get() = _caList


    init {
        loadData()
    }


    fun loadData() {
        viewModelScope.launch {
            tryCatch({
                val response = withContext(schedulerProvider.dispatchersIO()) {
                    accountRepository.getAllAccounts()
                }
                _accountList.value = response
               /*    _caList.value =  _accountList.value.map { bank ->
                       Category(
                           type = if (bank.isCA == "1") "Crédit Agricole" else "Autre Banques",
                           name = bank.name,
                           items = bank.accounts
                       )
                   }*/
                Logger.d(ContentValues.TAG, response.toString())
                Logger.e(ContentValues.TAG, _caList.value.first().name)
                Logger.e(ContentValues.TAG, _caList.value.size.toString())

            }, {
                Logger.d(ContentValues.TAG, it.toString())
            })
        }
    }


}
