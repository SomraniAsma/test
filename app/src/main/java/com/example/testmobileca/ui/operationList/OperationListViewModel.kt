package com.example.testmobileca.ui.operationList

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.testmobileca.base.BaseViewModel
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.Operation
import com.example.testmobileca.global.listener.ClickListener
import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.listener.ToolBarListener
import com.example.testmobileca.global.utils.ExtraKeys
import com.example.testmobileca.global.utils.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class OperationListViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    schedulerProvider: SchedulerProvider,
) : BaseViewModel(application, schedulerProvider) , ToolBarListener, ClickListener<Account> {


    private val _account: MutableStateFlow<Account> =
        MutableStateFlow(savedStateHandle.get<Account>(ExtraKeys.AccountActivity.ACCOUNT_EXTRA_OPERATION_KEY)!!)
    val account: StateFlow<Account> get() = _account

    private val _operationList: MutableStateFlow<List<Operation>> = MutableStateFlow(emptyList())
    val operationList: StateFlow<List<Operation>> get() = _operationList




    /**
     *  Init data
     */
    init {
        sortList()
    }


    /**
     * Back navigation on toolbar icon click
     */
    override fun onBackClicked() {
        navigate(Navigation.Back)
    }


    /**
     * Apply filters to data list
     */
    private fun sortList(){
        _account.value.operations
            .sortedWith(compareByDescending<Operation> { it.date }
                .thenBy { it.title }).also { _operationList.value = it }
    }


}
