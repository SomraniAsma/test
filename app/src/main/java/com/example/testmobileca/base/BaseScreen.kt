package com.example.testmobileca.base

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.testmobileca.ui.theme.MobileCaTheme
import com.google.accompanist.insets.ProvideWindowInsets
/**
 * The Home screen.
 */
@ExperimentalMaterialApi
@Composable
fun <T> BaseScreen(
    viewModel: T,
    topBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    body: @Composable (PaddingValues) -> Unit
) where T : BaseViewModel {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    ProvideWindowInsets {
        MobileCaTheme {
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier.systemBarsPadding(),
                floatingActionButton = fab,
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = {
                    scaffoldState.snackbarHostState
                }
            ) {
                body(it)
            }
        }
    }
    RegisterBaseObservers(viewModel, scaffoldState)
}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalMaterialApi
@Composable
private fun <T> RegisterBaseObservers(
    viewModel: T,
    scaffoldState: ScaffoldState
) where T : BaseViewModel {

     val coroutineScope = rememberCoroutineScope()

}

