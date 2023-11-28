package com.example.testmobileca.ui.operationList

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testmobileca.R
import com.example.testmobileca.base.BaseScreen
import com.example.testmobileca.data.model.BankCategory
import com.example.testmobileca.data.model.Operation
import com.example.testmobileca.global.enumeration.NavBottomItem
import com.example.testmobileca.global.listener.ToolBarListener
import com.example.testmobileca.global.utils.dateFormat
import com.example.testmobileca.ui.accountList.AccountListViewModel
import com.example.testmobileca.ui.component.BottomNavigationBar
import com.example.testmobileca.ui.component.ListItem
import com.example.testmobileca.ui.component.ToolbarComponent
import com.example.testmobileca.ui.theme.black
import com.example.testmobileca.ui.theme.grayBackground
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
fun TopBar(viewModel: OperationListViewModel){
    ToolbarComponent(
        endIcon = Icons.Filled.ArrowBackIos,
        toolBarListener = viewModel,
        menuType = "menuType"
    )
}
@Composable
fun BodyContent(viewModel: OperationListViewModel) {
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

@OptIn(ExperimentalComposeUiApi::class)
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
        val paddingbottomGuideline = createGuidelineFromBottom(0.1f)
        val account by viewModel.account.collectAsState()
        val operationList by viewModel.operationList.collectAsState()


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
                text = "${account.balance.toString()} €" ,
                fontSize = 28.sp,
                fontWeight =  FontWeight.Bold,
                maxLines = 1,
                color = black,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(start = 0.dp, top = 20.dp)
            )
            Text(
                text = account.label,
                fontSize = 20.sp,
                fontWeight =  FontWeight.SemiBold,
                maxLines = 1,
                color = black,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 0.dp, bottom = 10.dp)
            )
        }

        Box(
           modifier = Modifier.constrainAs(list) {
                linkTo(end = paddingEndVerticalGuideline, start = paddingStartVerticalGuideline)
                top.linkTo(header.bottom)
                width = Dimension.fillToConstraints
            }
                    .padding(bottom = 74.dp)
        ) {
                    LazyColumn{
                        items(operationList) {
                            ListItem(
                                collapsable = false,
                                text = it.title,
                                extraText = it.amount,
                                subText = dateFormat(it.date.toLong()),
                                collapsed = false,
                                onClickActionBlock = {//TODO add treatment
                                     }
                            )
                }
                    }
                }
            }

        }

