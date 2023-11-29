package com.example.testmobileca.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobileca.R
import com.example.testmobileca.global.listener.ToolBarListener
import com.example.testmobileca.ui.theme.MobileCaTheme
import com.example.testmobileca.ui.theme.selectedNavItem
import com.example.testmobileca.ui.theme.shadowedGray


/**
 * topBar
 * @param startIcon Icon
 * @param toolBarListener  listener for toolbar click
 */

@ExperimentalAnimationApi
@Composable
fun ToolbarComponent(
    startIcon: ImageVector?,
    toolBarListener: ToolBarListener,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(shadowedGray)
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
                onClick = { toolBarListener.onBackClicked() }
            ) {
                startIcon?.let {
                    Icon(
                        imageVector = startIcon,
                        contentDescription = "",
                        tint = selectedNavItem,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(start = 1.dp, top = 1.dp, bottom = 1.dp)
                    )
                }
            }

            Text(
                text = stringResource(R.string.operation_list_nav_back_title),
                color = selectedNavItem,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TobBarPreview() {
    MobileCaTheme() {

    }}
