package com.example.testmobileca.ui.accountList

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.testmobileca.base.BaseActivity
import com.example.testmobileca.global.utils.ExtraKeys
import com.example.testmobileca.global.utils.Navigation
import com.example.testmobileca.ui.operationList.OperationListActivity
import com.example.testmobileca.ui.operationList.OperationListScreen
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
        when (navigationTo) {
            is Navigation.Back -> {
                finish()
            }
            is Navigation.OperationListNavigation -> {
                operationListNavigation(navigationTo)
            }
            else -> {}
        }
    }


private fun operationListNavigation(navigationTo: Navigation.OperationListNavigation) {
    Intent(this, OperationListActivity::class.java).run {
        putExtra(
            ExtraKeys.AccountActivity.ACCOUNT_EXTRA_OPERATION_KEY,
            navigationTo.account
        )
        startActivity(this)
    }

}


override fun onDestroy() {
    super.onDestroy()
}
}