package com.example.testmobileca.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobileca.R
import com.example.testmobileca.data.model.BankCategory
import com.example.testmobileca.ui.theme.grayHeader
import com.example.testmobileca.ui.theme.grayText
import com.example.testmobileca.ui.theme.shadowedGray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollapsableLazyColumn(
    sections: List<BankCategory>,
    modifier: Modifier = Modifier
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }

        LazyColumn(modifier.fillMaxSize()){
            val mainGroup = sections.groupBy { it.category }
            mainGroup.forEach { (category, groupedData) ->
                stickyHeader {
                    CategoryHeader(text = category)
                }
                groupedData.forEach { dataItem ->
                    val collapsed = collapsedState[dataItem.index]

                    item(key = "header_${dataItem.index} ${category}") {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.Start),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                                .clickable {
                                    collapsedState[dataItem.index] = !collapsed
                                }
                        ) {
                            Text(
                                text = dataItem.bankName,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                modifier=Modifier.weight(2f),
                                )
                            Text(
                                text = "12348.65 €",
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                color = shadowedGray,
                                modifier=Modifier.weight(0.75f),
                            )
                            Icon(
                                Icons.Default.run {
                                    if (collapsed)
                                        ExpandMore
                                    else
                                        ExpandLess
                                },
                                contentDescription = "",
                                modifier=Modifier.padding(end=10.dp)
                                    .size(20.dp)
                                    .weight(0.5f),
                                tint = Color.LightGray,
                            )
                        }
                        Divider()
                    }
                    if (!collapsed) {
                        items(dataItem.accounts) { row ->
                            Row {
                                Spacer(modifier = Modifier.size(MaterialIconDimension.dp))
                                Text(
                                    row.label,
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                )
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }



const val MaterialIconDimension = 24f


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





