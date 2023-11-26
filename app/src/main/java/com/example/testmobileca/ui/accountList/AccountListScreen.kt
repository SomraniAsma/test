package com.example.testmobileca.ui.accountList

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testmobileca.R
import com.example.testmobileca.base.BaseScreen
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.BanksResponse
import com.example.testmobileca.data.model.Category
import com.example.testmobileca.global.enumeration.NavBottomItem
import com.example.testmobileca.ui.component.BottomNavigationBar
import com.example.testmobileca.ui.theme.*
import kotlinx.coroutines.NonDisposableHandle.parent

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
            route = "",
        ),
        NavBottomItem(
            label = stringResource(R.string.nav_bar_simulation),
            icon = Icons.Filled.StarRate,
            route = "",
        ),
        NavBottomItem(
            label = stringResource(R.string.nav_bar_free),
            icon = Icons.Filled.StarRate,
            route = "",
        )
    )


    BaseScreen(
        viewModel = viewModel,
        topBar = { },
        fab = {},
        bottomBar = { BottomNavigationBar(bottomNavItems) }
    ) {
        BodyContent(viewModel)

    }
}

@Composable
fun BodyContent(viewModel: AccountListViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = MaterialTheme.colors.background)

        )
        {
            Column(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .align(Alignment.TopCenter)
            ) {
                Body(viewModel)
            }
        }
    }
}

@Composable
fun Body(viewModel: AccountListViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        var (header, list) = createRefs()
        val headerRowHorizontalGuideline = createGuidelineFromTop(0.20f)
        val paddingStartVerticalGuideline = createGuidelineFromStart(0.04f)
        val paddingEndVerticalGuideline = createGuidelineFromStart(0.96f)
        val dataList by viewModel.accountList.collectAsState(emptyList())



        Box(modifier = Modifier
            .constrainAs(header)
            {
                height = Dimension.wrapContent
                linkTo(end = parent.end, start = parent.start)
                linkTo(bottom = headerRowHorizontalGuideline, top = parent.top)
                width = Dimension.matchParent
                height = Dimension.fillToConstraints
            }
            .background(shadowedGray)
        ) {
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


        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            modifier = Modifier.constrainAs(list) {
                linkTo(end = paddingEndVerticalGuideline, start = paddingStartVerticalGuideline)
                top.linkTo(header.bottom)
                width = Dimension.matchParent
            }) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            ) {
                CategorizedLazyColumn(categories = dataList)
            }

        }

    }
}

/**
 * categorized list
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories: List<BanksResponse>,
) {
    val mainGroup = categories.groupBy { it.isCA }
    LazyColumn {
        mainGroup.forEach { (type, groupedData) ->
            stickyHeader {
                CategoryHeader(text = type)
            }
            items(groupedData) { item ->
                Item(item = item)
            }
        }
    }
}

@Composable
fun Item(item: BanksResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //TODO add treatment
            },
        backgroundColor = white,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(50.dp, Alignment.Start),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .align(Alignment.Start)
            ) {
                Text(
                    text = item.name,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "123458.65 €",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = shadowedGray
                )
            }
        }
    }
}


@Composable
private fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (text == "1") stringResource(R.string.account_list_screen_ca_category) else stringResource(
                    R.string.account_list_screen_other_category),
        fontSize = 16.sp,
        color = grayText,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(grayHeader)
            .padding(10.dp)
    )
}


