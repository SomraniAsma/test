package com.example.testmobileca.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca.global.enumeration.NavBottomItem
import androidx.compose.ui.unit.sp
import com.example.testmobileca.R
import com.example.testmobileca.ui.theme.*

/**
 * Bottom navigation bar
 * @param bottomNavItems  list of nav items
 */
@Composable
fun BottomNavigationBar(
    bottomNavItems: List<NavBottomItem>,
    ) {

    BottomNavigation(
        modifier = Modifier.height(72.dp),
        backgroundColor = shadowedGray
    )
    {
        bottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = navItem.selected,
                onClick = {
                    //TODO add treatment here
                },
                icon = {
                    navItem.icon?.let {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.label,
                            modifier = Modifier
                                .padding(top = 1.dp, start = 4.dp, end = 4.dp)
                                .size(27.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text = navItem.label,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        maxLines = 1,
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                },
                selectedContentColor = selectedNavItem,
                unselectedContentColor = grayStar,
                alwaysShowLabel = true
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavPreview() {
    MobileCaTheme() {
        BottomNavigationBar(
             listOf(
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

        )
    }
}