package com.example.testmobileca.ui.accountList

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.testmobileca.base.BaseActivity
import com.example.testmobileca.global.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi


@AndroidEntryPoint
class AccountListActivity : BaseActivity() {

    private val viewModel: AccountListViewModel by viewModels()

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountListScreen(viewModel)
        }

        registerBaseObservers(viewModel)

    }

    override fun navigate(navigationTo: Navigation) {
        super.navigate(navigationTo)
        when(navigationTo){
            is Navigation.Back -> {
                finish()
            }
            else -> {
                //..
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
       }
}