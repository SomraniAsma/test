package com.example.testmobileca.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobileca.R
import com.example.testmobileca.global.listener.ToolBarListener
import com.example.testmobileca.ui.theme.black
import com.example.testmobileca.ui.theme.selectedNavItem


@ExperimentalAnimationApi
@Composable
fun ToolbarComponent(
    endIcon: ImageVector? ,
    toolBarListener: ToolBarListener,
    menuType: String,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .alpha(1f)
        ) {
            IconButton(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .background(Color.Transparent)
                    .alpha(1f),
                onClick = { toolBarListener.onBackClicked(menuType) }
            ) {
                endIcon?.let {
                    Icon(
                        imageVector = endIcon,
                        contentDescription = "",
                        tint = selectedNavItem,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 1.dp, top = 1.dp, bottom = 1.dp)
                    )
                }
            }

            Text(
                text = stringResource(R.string.operation_list_nav_back_title),
                color = selectedNavItem,
                fontSize = 12.sp

            )
        }
    }



}

