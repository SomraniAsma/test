package com.example.testmobileca.ui.operationList

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
@Preview(device = "id:Nexus 5X")
fun PreviewOperationScreen() {
    return OperationListScreen(viewModel = hiltViewModel())
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OperationListScreen(viewModel: OperationListViewModel = hiltViewModel()) {}
