package com.example.testmobileca.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.testmobileca.ui.theme.grayStar
import com.example.testmobileca.ui.theme.shadowedGray
/**
 * listItem component
 * @param collapsable  boolean
 * @param text  String
 * @param subText  String
 * @param extraText  string
 * @param collapsed  Boolean
 * @param onClickActionBlock
 */
@Composable
fun ListItem(
    collapsable: Boolean,
    text: String,
    subText: String,
    extraText: String,
    collapsed: Boolean,
    onClickActionBlock: ((Boolean) -> Unit)? = null,
    ) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween/* (1.dp, Alignment.Start)*/,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            .clickable {
                onClickActionBlock?.invoke(!collapsed)
            }
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
            modifier = Modifier.weight(1.5f)
        ) {


            Text(
                text = text,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = grayStar,
            )
            Text(
                text = subText,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = grayStar,
                modifier = Modifier
                    .padding(start = 4.dp),
            )
        }
        Text(
            text = "${extraText} €",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = shadowedGray,
            modifier = Modifier.weight(0.5f),
        )

        if (collapsable) {
            Icon(
                Icons.Default.run {
                    if (collapsed)
                        ExpandMore
                    else
                        ExpandLess
                },
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(20.dp)
                    .weight(0.5f),
                tint = Color.LightGray,
            )
        }

    }
    Divider()
}