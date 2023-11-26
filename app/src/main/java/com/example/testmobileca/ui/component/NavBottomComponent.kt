package com.example.testmobileca.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.testmobileca.global.enumeration.NavBottomItem
import androidx.compose.ui.unit.sp
import com.example.testmobileca.ui.theme.grayBackground
import com.example.testmobileca.ui.theme.grayStar
import com.example.testmobileca.ui.theme.grayText
import com.example.testmobileca.ui.theme.selectedNavItem


@Composable
fun BottomNavigationBar(
    bottomNavItems: List<NavBottomItem>,
) {
    var isSelected by remember { mutableStateOf(false) }
    val textColor= if(isSelected) selectedNavItem else grayText
        val iconTint= if(isSelected ) selectedNavItem else grayStar
    BottomNavigation(
        modifier = Modifier.height(50.dp),
        backgroundColor = grayBackground
    )
    {
        bottomNavItems.forEach { navItem ->

            BottomNavigationItem(
                selected = true,
                onClick = {
                    //TODO add treatment here
                },
                icon = {
                    navItem.icon?.let {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.label,
                            tint= iconTint,
                            modifier = Modifier
                                .padding(top=4.dp, start = 4.dp, end = 4.dp)
                                .size(20.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text = navItem.label,
                        textAlign = TextAlign.Center,
                        fontSize = 8.sp,
                        maxLines = 1,
                        color = textColor
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}