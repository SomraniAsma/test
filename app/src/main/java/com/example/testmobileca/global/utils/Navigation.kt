package com.example.testmobileca.global.utils

sealed class Navigation {

    object Back : Navigation()

    object MenuNavigation : Navigation()
}

