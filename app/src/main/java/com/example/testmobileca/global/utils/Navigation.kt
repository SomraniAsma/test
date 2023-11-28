package com.example.testmobileca.global.utils

import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.Operation

sealed class Navigation {

    object Back : Navigation()

    object AccountListNavigation : Navigation()

    data class OperationListNavigation (val account: Account) : Navigation()

}
