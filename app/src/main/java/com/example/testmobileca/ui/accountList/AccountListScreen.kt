package com.example.testmobileca.ui.accountList

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testmobileca.R
import com.example.testmobileca.base.BaseScreen
import com.example.testmobileca.global.enumeration.NavBottomItem
import com.example.testmobileca.ui.component.BottomNavigationBar
import com.example.testmobileca.ui.component.Loader

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

        val localFocusManager = LocalFocusManager.current
        // val isLoading by viewModel.isLoading

        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = MaterialTheme.colors.background)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }
        )
        {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
                    .align(Alignment.TopCenter)
            ) {
                Body(viewModel)
            }
            // Loader(isLoading = isLoading)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Body(viewModel: AccountListViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize(1f)
            .verticalScroll(rememberScrollState())

    ) {
        var (header, identifier, login, password, menu, footer) = createRefs()
        val logoRowHorizontalGuideline = createGuidelineFromTop(0.05f)
        val paddingStartVerticalGuideline = createGuidelineFromStart(0.04f)
        val paddingEndVerticalGuideline = createGuidelineFromStart(0.96f)
        val paddingbottomGuideline = createGuidelineFromBottom(0.1f)

        Box(modifier = Modifier.constrainAs(header) {
            height = Dimension.wrapContent
            linkTo(end = parent.end, start = parent.start)
            linkTo(bottom = logoRowHorizontalGuideline, top = parent.top)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }) {
            Text(
                text = "title",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterVertically),
            modifier = Modifier.constrainAs(menu) {
                linkTo(end = paddingEndVerticalGuideline, start = paddingStartVerticalGuideline)
                top.linkTo(header.bottom)
                width = Dimension.fillToConstraints
            }) {
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            ) {
                Text(
                    text = "text",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.Start)
                )
                Box(
                    modifier = Modifier
                        .animateContentSize()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(13.5.dp))
                        .padding(end = 2.dp, start = 2.dp, bottom = 2.dp)
                        .background(color = Color.Gray)
                        .border(1.dp, (Color.Yellow), shape = RoundedCornerShape(6.dp))
                )
                {
                    //
                }
            }

        }

    }
}