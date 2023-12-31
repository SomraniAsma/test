package com.example.testmobileca.ui.operationList

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testmobileca.R
import com.example.testmobileca.base.BaseScreen
import com.example.testmobileca.global.enumeration.NavBottomItem
import com.example.testmobileca.global.utils.dateFormat
import com.example.testmobileca.ui.component.BottomNavigationBar
import com.example.testmobileca.ui.component.ListItem
import com.example.testmobileca.ui.component.ToolbarComponent
import com.example.testmobileca.ui.theme.black
import com.example.testmobileca.ui.theme.shadowedGray

@Composable
@Preview(device = "id:Nexus 5X")
private fun PreviewAccountScreen() {
    return OperationListScreen(viewModel = hiltViewModel())
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OperationListScreen(viewModel: OperationListViewModel = hiltViewModel()) {

/*
  navBar component
*/
    val bottomNavItems = listOf(
        NavBottomItem(
            label = stringResource(R.string.nav_bar_accounts),
            icon = Icons.Filled.StarRate,
            selected = false,
        ),
        NavBottomItem(
            label = stringResource(R.string.nav_bar_simulation),
            icon = Icons.Filled.StarRate,
            selected = true,
        ),
        NavBottomItem(
            label = stringResource(R.string.nav_bar_free),
            icon = Icons.Filled.StarRate,
            selected = false,
        )
    )


    BaseScreen(
        viewModel = viewModel,
        topBar = { TopBar(viewModel) },
        fab = {},
        bottomBar = { BottomNavigationBar(bottomNavItems) }
    ) {
        Body(viewModel)

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopBar(viewModel: OperationListViewModel) {
    ToolbarComponent(
        startIcon = Icons.Filled.ArrowBackIos,
        toolBarListener = viewModel,
    )
}


@Composable
fun Body(viewModel: OperationListViewModel) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize(1f)

    ) {
        var (header, list) = createRefs()
        val headerRowHorizontalGuideline = createGuidelineFromTop(0.22f)
        val paddingStartVerticalGuideline = createGuidelineFromStart(0.04f)
        val paddingEndVerticalGuideline = createGuidelineFromStart(0.96f)
        val account by viewModel.account.collectAsState()
        val operationList by viewModel.operationList.collectAsState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
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
                text = "${account.balance} €",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = black,
                modifier = Modifier
                    .weight(2f)
            )
            Text(
                text = account.label,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                color = black,
                modifier = Modifier
                    .weight(1f)
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(list) {
                    linkTo(end = paddingEndVerticalGuideline, start = paddingStartVerticalGuideline)
                    top.linkTo(header.bottom)
                    width = Dimension.fillToConstraints
                }
                .padding(bottom = 74.dp)
        ) {
            LazyColumn {
                items(operationList) {
                    ListItem(
                        collapsable = false,
                        text = it.title,
                        extraText = it.amount,
                        subText = dateFormat(it.date.toLong(), "dd/MM/yy"),
                        collapsed = false,
                        onClickActionBlock = {//TODO add treatment
                        }
                    )
                }
            }
        }
    }

}

