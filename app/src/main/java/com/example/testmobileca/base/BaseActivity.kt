package com.example.testmobileca.base

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testmobileca.global.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlin.reflect.KClass


@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {

    @InternalCoroutinesApi
    protected fun registerBaseObservers(viewModel: ViewModel) {
        if (viewModel is BaseViewModel) {
            registerNavigation(viewModel)
        }
    }

    /**
     * Observe [BaseViewModel.navigation] and navigate.
     *
     * @param viewModel [BaseViewModel]
     */
    @InternalCoroutinesApi
    private fun registerNavigation(viewModel: BaseViewModel) {
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModel.navigation.collect{
                    navigate(it)
                }
            }
        }
    }

    @CallSuper
    open fun navigate(navigationTo: Navigation) {
        //..
    }

    /**
     * startActivity to class
     * @param kClass activity to navigate to
     * @param shouldFinish should finish current activity
     */
    fun navigateToActivity(kClass: KClass<out Activity>, shouldFinish: Boolean = false) {
        startActivity(Intent(this, kClass.java))
        if (shouldFinish) finish()
    }
}
