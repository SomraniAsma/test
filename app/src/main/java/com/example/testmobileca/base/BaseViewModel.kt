package com.example.testmobileca.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.utils.Navigation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel(
    application: Application,
    schedulerProvider: SchedulerProvider,
) : AndroidViewModel(application) {

    //for resource access only
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