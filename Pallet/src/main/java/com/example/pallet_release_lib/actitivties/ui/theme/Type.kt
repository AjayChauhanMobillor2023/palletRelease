package com.example.pallet_release_lib.actitivties.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pallet_release_lib.R
import com.example.pallet_release_lib.actitivties.FirstComposeActivityPalletRelease

// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        ))



        object TextStyles {
                val normalTextStyle = TextStyle(
                        fontSize = 14.sp,
                        color = darkGrey
                )
                val normalLightTextStyle = TextStyle(
                        fontSize = 13.sp,
                        color = grey
                )
                val normalWhiteTextStyleBold = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                )



                val smallNormalTextStyle = TextStyle(
                        fontSize = 10.sp,
                        color = darkMediumGrey
                )

                val smallMediumNormalTextStyle = TextStyle(
                        fontSize = 12.sp,
                        color = darkMediumGrey
                )
                val smallMediumRedTextStyle = TextStyle(
                        fontSize = 12.sp,
                        color = evenLighterRedStuff
                )

                val smallLightColoredNormalTextStyle = TextStyle(
                        fontSize = 10.sp,
                        color = FirstComposeActivityPalletRelease.PRIMARY_COLOR
                )
                val smallMediumLightColoredNormalTextStyle = TextStyle(
                        fontSize = 12.sp,
                        color = FirstComposeActivityPalletRelease.PRIMARY_COLOR
                )

                val smallNormalGreyTextStyle = TextStyle(
                        fontSize = 10.sp,
                        color = FirstComposeActivityPalletRelease.SECONDARY_COLOR
                )

                val normalTextStyleBold = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                )

                val normalTextStyleRed = TextStyle(
                        fontSize = 14.sp,
                        color = FirstComposeActivityPalletRelease.PRIMARY_COLOR
                )

                val titleTextStyle = TextStyle(
                        fontSize = 18.sp,
                        color = darkGrey,
                        textAlign = TextAlign.Center
                )

                val whiteTitleTextStyle = TextStyle(
                        fontSize = 18.sp,
                        color = white,
                        textAlign = TextAlign.Center
                )

                val subTextStyle = TextStyle(
                        fontSize = 12.sp,
                        color = FirstComposeActivityPalletRelease.SECONDARY_COLOR
                )

                val subTextDarkStyle = TextStyle(
                        fontSize = 12.sp,
                        color = darkMediumGrey
                )

                val highlightedTextStyle = TextStyle(
                        fontSize = 14.sp,
                        color = FirstComposeActivityPalletRelease.PRIMARY_COLOR,
                        textAlign = TextAlign.Center
                )

                val semiHighlightedTextStyle = TextStyle(
                        fontSize = 12.sp,
                        color = FirstComposeActivityPalletRelease.PRIMARY_COLOR,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                )

                val wildcardTextStyle = TextStyle(
                        fontSize = 14.sp,
                        color = darkGrey,
                        textAlign = TextAlign.Center,
                )
                val bigWildcardTextStyle = TextStyle(
                        fontSize = 24.sp,
                        color = darkGrey,
                        textAlign = TextAlign.Center,
                )
                val whiteWildcardTextStyle = TextStyle(
                        fontSize = 24.sp,
                        color = white,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                )
                val smallWildcardTextStyle = TextStyle(
                        fontSize = 10.sp,
                        color = darkGrey,
                        textAlign = TextAlign.Center,
                )
        }
