package com.example.testmobileca.ui.operationList

import android.app.Application
import com.example.testmobileca.base.BaseViewModel
import com.example.testmobileca.global.listener.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OperationListViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
) : BaseViewModel(application, schedulerProvider) {}
