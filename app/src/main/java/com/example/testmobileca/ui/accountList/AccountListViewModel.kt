package com.example.testmobileca.ui.accountList

import android.app.Application
import android.content.ContentValues
import com.example.testmobileca.base.BaseViewModel
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.BanksResponse
import com.example.testmobileca.data.model.BankCategory
import com.example.testmobileca.data.model.Operation
import com.example.testmobileca.data.repository.abs.AccountRepository
import com.example.testmobileca.global.listener.ClickListener
import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.utils.Logger
import com.example.testmobileca.global.utils.Navigation
import com.example.testmobileca.global.utils.tryCatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AccountListViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val accountRepository: AccountRepository
) : BaseViewModel(application, schedulerProvider), ClickListener<Account> {

    private val _accountList: MutableStateFlow<List<BanksResponse>> = MutableStateFlow(emptyList())
    val accountList: StateFlow<List<BanksResponse>> get() = _accountList

    private val _categorizedList: MutableStateFlow<List<BankCategory>> = MutableStateFlow(emptyList())
    val categorizedList: StateFlow<List<BankCategory>> get() = _categorizedList


    /**
     * Init data
     */
    init {
        loadData()
    }


    /**
     *
     */
    override fun onItemClicked(item: Account) {
        navigate(Navigation.OperationListNavigation(item))
    }


    /**
     * Fetch data from server
     */
    fun loadData() {
        viewModelScope.launch {
            tryCatch({
                val response = withContext(schedulerProvider.dispatchersIO()) {
                    accountRepository.getAllAccounts()
                }
                //_accountList.value = response
                var index = 0
                _categorizedList.value = response.map { bank ->
                    BankCategory(
                        index = index++,
                        category = bank.isCA,
                        bankName = bank.name,
                        accounts = bank.accounts
                    )
                }
                Logger.d(ContentValues.TAG, response.toString())
            }, {
                Logger.e(ContentValues.TAG, it.stackTraceToString())
            })
        }
    }


}
