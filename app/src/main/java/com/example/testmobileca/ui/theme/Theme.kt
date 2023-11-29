package com.example.testmobileca.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = white,
    primaryVariant = grayText,
    secondary = paleWhite,
    secondaryVariant= white,
    onPrimary = onPrimaryColorBlack,
    onBackground= shadowedGray,


)

@Composable
fun MobileCaTheme( content: @Composable () -> Unit) {
    MaterialTheme(
            colors = LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}

fun navBarText(color: Color, textAlign: TextAlign) = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    textAlign = textAlign,
    color = color
)


fun textFieldText(color: Color, textAlign: TextAlign) = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    textAlign = textAlign,
    color = color
)



fun caption(color: Color, textAlign: TextAlign) = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 27.sp,
    textAlign = textAlign,
    color = color
)