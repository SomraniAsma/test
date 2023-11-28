package com.example.testmobileca.ui.accountList

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testmobileca.R
import com.example.testmobileca.base.BaseScreen
import com.example.testmobileca.data.model.BankCategory
import com.example.testmobileca.data.model.BanksResponse
import com.example.testmobileca.global.enumeration.NavBottomItem
import com.example.testmobileca.ui.component.BottomNavigationBar
import com.example.testmobileca.ui.component.CollapsableLazyColumn
import com.example.testmobileca.ui.theme.*
import java.util.Locale.Category

@Composable
@Preview(device = "id:Nexus 5X")
private fun PreviewAccountScreen() {
    return AccountListScreen(viewModel = hiltViewModel())
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountListScreen(viewModel: AccountListViewModel = hiltViewModel()) {

/*
  navBar component
*/
    val bottomNavItems = listOf(
        NavBottomItem(
            label = stringResource(R.string.nav_bar_accounts),
            icon = Icons.Filled.StarRate,
            selected = true,

        ),
        NavBottomItem(
            label = stringResource(R.string.nav_bar_simulation),
            icon = Icons.Filled.StarRate,
            selected = false,
        ),
        NavBottomItem(
            label = stringResource(R.string.nav_bar_free),
            icon = Icons.Filled.StarRate,
            selected = false,
        )
    )

    BaseScreen(
        viewModel = viewModel,
        topBar = { },
        fab = {},
        bottomBar = { BottomNavigationBar(bottomNavItems) }
    ) {
        Body(viewModel)

    }
}

@Composable
fun Body(viewModel: AccountListViewModel) {
    val dataList by viewModel.categorizedList.collectAsState(emptyList())

        Column( horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()

    ){/*
                    ** Header
                    */
            Box(modifier = Modifier
               .fillMaxWidth()
                .weight(0.75f)
                .background(shadowedGray)
            ){

            Text(
                text = stringResource(R.string.account_list_screen_title),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = black,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp, bottom = 5.dp)
            )
        }
            /*
             ** List
             */
        Box(  modifier = Modifier
            .padding(bottom = 74.dp)
            .weight(3f)
        ) {
            CollapsableLazyColumn(
                sections= dataList,
                onAccountClickedActionBlock = {viewModel.onItemClicked(it)}
            )
        }
   }
}

