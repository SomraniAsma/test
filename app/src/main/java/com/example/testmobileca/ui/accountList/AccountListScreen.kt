package com.example.testmobileca.ui.accountList

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
@Preview(device = "id:Nexus 5X")
private fun PreviewAccountScreen() {
    return AccountListScreen(viewModel = hiltViewModel())
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountListScreen(viewModel: AccountListViewModel = hiltViewModel()) {}
