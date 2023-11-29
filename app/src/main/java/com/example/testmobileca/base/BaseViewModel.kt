package com.example.testmobileca.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.testmobileca.data.repository.abs.AccountRepository
import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.utils.Logger
import com.example.testmobileca.global.utils.Navigation
import com.example.testmobileca.retrofit.EndPointInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException


abstract class BaseViewModel(
    application: Application,
    protected val schedulerProvider: SchedulerProvider,
) : AndroidViewModel(application) {

    private val job = SupervisorJob()
    protected val viewModelScope = CoroutineScope(job + schedulerProvider.dispatchersUI())

    @SuppressLint("StaticFieldLeak")
    protected val applicationContext = application.applicationContext!!

    private val _navigation = MutableSharedFlow<Navigation>(replay = 0)
    val navigation: SharedFlow<Navigation>
        get() = _navigation

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing.asStateFlow()




    /**
     * Used for navigation events.
     * @param navigationTo navigate The new destination.
     */
    fun navigate(navigationTo: Navigation) {
        viewModelScope.launch {
            _navigation.emit(navigationTo)
        }
    }


    /**
     * handler error
     * @throws throwable error
     */
    protected fun handleThrowable(throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                Logger.e("HttpException", throwable.toString())
            }
            is EndPointInterceptor.NetworkNotFoundException -> {
                Logger.e("NetworkNotFoundException", throwable.toString())
            }
            else -> {
                Logger.e("other Error", throwable.toString())
            }
        }
    }


    /**
     * UpdateRefresh state
     * @param refreshState boolean
     */
    protected fun updateRefreshState(refreshState: Boolean) = viewModelScope.launch {
        _isRefreshing.emit(refreshState)
    }


}