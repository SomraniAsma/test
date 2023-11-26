package com.example.testmobileca.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.utils.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


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


    /**
     * Used for navigation events.
     * @param navigationTo The new destination.
     */
    fun navigate(navigationTo: Navigation) {
        viewModelScope.launch {
            _navigation.emit(navigationTo)
        }
    }

}