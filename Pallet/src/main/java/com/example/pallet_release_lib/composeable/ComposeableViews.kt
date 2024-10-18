package com.example.pallet_release_lib.composeable

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pallet_release_lib.AppConstants
import com.example.pallet_release_lib.R
import com.example.pallet_release_lib.actitivties.FirstComposeActivityPalletRelease
import com.example.pallet_release_lib.actitivties.ui.theme.TextStyles
import com.example.pallet_release_lib.actitivties.ui.theme.darkPurpleStuff
import com.example.pallet_release_lib.actitivties.ui.theme.lightpurplestuff
import com.example.pallet_release_lib.model.DataGetMaterialForEmptyPalletModel

@Composable
fun TopNavigationBar(
        headText : String,
        contextActivity : Activity
) {

        val context = LocalContext.current

        Card(
                modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = FirstComposeActivityPalletRelease.PRIMARY_COLOR)

        ) {

                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {

                        IconButton(onClick = {
//                Toast.makeText(context,
//                    "testing"
//                    , Toast.LENGTH_SHORT).show()

                                contextActivity.onBackPressed()
                        }) {
                                Icon(
                                        painter = painterResource(id = R.drawable.back),
                                        contentDescription = "",
                                        tint = FirstComposeActivityPalletRelease.SECONDARY_COLOR
                                )
                        }

                        Text(text = headText, color = FirstComposeActivityPalletRelease.SECONDARY_COLOR, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.padding(20.dp))

                }
        }
}

@Composable
fun ScanHintCard(hint : String){
        CurvedBoxWithDottedBorder(280.dp,20.dp) {
                Column(Modifier.padding(0.dp,0.dp,0.dp,0.dp)) {
                        AnimatedPreloader()
                        Text(
                                text = hint,
                                style = TextStyles.wildcardTextStyle,
                                modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )
                }

        }
}

@Composable
fun CurvedBoxWithDottedBorder(height: Dp, cornerRadius : Dp, content: @Composable () -> Unit) {



        Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .height(height)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .clip(RoundedCornerShape(cornerRadius))
                        .background(Color.White)
        ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                        val borderColor = Color(0xFFFFDADE)
                        val dotRadius = 6f
                        val dotSpacing = 10f

                        // Draw the dotted border
                        drawDottedBorder(
                                borderColor = borderColor,
                                dotRadius = dotRadius,
                                dotSpacing = dotSpacing,
                                cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                                topLeft = Offset(0f, 0f),
                                size = Size(size.width, size.height)
                        )
                }

                // Content inside the box
                Box(
                        modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                        contentAlignment = Alignment.Center
                ) {
                        content()
                }
        }
}

private fun DrawScope.drawDottedBorder(
        borderColor: Color,
        dotRadius: Float,
        dotSpacing: Float,
        cornerRadius: CornerRadius,
        topLeft: Offset,
        size: Size
) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dotSpacing, dotSpacing), 0f)

        drawRoundRect(
                color = borderColor,
                cornerRadius = cornerRadius,
                style = Stroke(width = 1.dp.toPx(), pathEffect = pathEffect),
                topLeft = topLeft,
                size = size
        )
}

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier) {
        val preloaderLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scani))

        val preloaderProgress by animateLottieCompositionAsState(preloaderLottieComposition, iterations = LottieConstants.IterateForever, isPlaying = true)


        LottieAnimation(
                composition = preloaderLottieComposition,
                progress = preloaderProgress,
                modifier = modifier
                        .height(192.dp)
                        .width(240.dp)
        )
}

@Composable
fun StatusAndScanButton(
        currentStatus: String,
        onStatusChange: (String) -> Unit,
        onScanButtonClick: (String) -> Unit,
        deviceType: String,
        context: Context
) {
        var switchState by remember { mutableStateOf(currentStatus == "RFID") }
        val status = if (switchState) "RFID" else "QR Code"

        Box(
                modifier = Modifier
                        .fillMaxSize() // Make the Box fill the entire screen
                        .padding(start=10.dp,bottom=56.dp,end=10.dp), // Add padding around the Box
                contentAlignment = Alignment.BottomCenter // Align content to the bottom center
        ) {
                Row(
                        modifier = Modifier
                                .fillMaxWidth() // Make the Row fill the width of the Box
                                .padding(start = 24.dp, end = 24.dp), // Padding around the Row
                        horizontalArrangement = Arrangement.SpaceBetween // Space between the components
                ) {
                        // Status Switch
                        Column(
                                modifier = Modifier
                                        .align(Alignment.CenterVertically) // Center vertically in the Row
                        ) {
                                Text(
                                        text = "Mode: $status",
                                        modifier = Modifier.padding(bottom = 8.dp) // Adjust padding here
                                )
                                Switch(
                                        checked = switchState,
                                        onCheckedChange = { isChecked ->
                                                if (!deviceType.contains(AppConstants.industrialDevice) && isChecked) {
                                                        Toast.makeText(
                                                                context,
                                                                "You can't switch to RFID's using a mobile device. Please use Cipher Lab Device.",
                                                                Toast.LENGTH_LONG
                                                        ).show()
                                                } else {
                                                        switchState = isChecked
                                                        onStatusChange(if (isChecked) "RFID" else "QR Code") // Notify parent about status change
                                                }
                                        }
                                )
                        }

                        // Bin Scan Button
                        FloatingActionButton(
                                onClick = { onScanButtonClick(status) },
                                containerColor = lightpurplestuff,
                                modifier = Modifier
                                        .align(Alignment.CenterVertically) // Center vertically in the Row
                                        .padding(end = 16.dp) // Add padding for spacing from the edge
                        ) {
                                Image(
                                        painter = painterResource(id = R.drawable.binss),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(darkPurpleStuff),
                                        modifier = Modifier.size(24.dp)
                                )
                        }
                }
        }
}

@Composable
fun PalletReleaseAdapterCard(data : DataGetMaterialForEmptyPalletModel){
        Card(

                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 2.dp)

        )
        {
                Column(
                        modifier = Modifier
                                .padding(0.dp,12.dp)
                ) {
                        Row(
                                modifier = Modifier
                                        .padding(16.dp,0.dp)
                                        .height(24.dp)
                        ){
                                Text(
                                        text = "SUID",
                                        style = TextStyles.smallMediumNormalTextStyle,
                                        modifier = Modifier
                                                .width(100.dp)

                                )
                                Text(
                                        text = ":",
                                        style = TextStyles.smallMediumNormalTextStyle
                                )
                                Text(
                                        text = "  ${data.suid}",
                                        style = TextStyles.smallMediumNormalTextStyle

                                )
                        }
                        Row(
                                modifier = Modifier
                                        .padding(16.dp,0.dp)
                                        .height(24.dp)
                        ){
                                Text(
                                        text = "GRN",
                                        style = TextStyles.smallMediumNormalTextStyle,
                                        modifier = Modifier
                                                .width(100.dp)

                                )
                                Text(
                                        text = ":",
                                        style = TextStyles.smallMediumNormalTextStyle
                                )
                                Text(
                                        text = "  ${data.grnNumber}",
                                        style = TextStyles.smallMediumNormalTextStyle

                                )
                        }
                        Row(
                                modifier = Modifier
                                        .padding(16.dp,0.dp)
                                        .height(24.dp)
                        ){
                                Text(
                                        text = "Item Code/QTY",
                                        style = TextStyles.smallMediumNormalTextStyle,
                                        modifier = Modifier
                                                .width(100.dp)

                                )
                                Text(
                                        text = ":",
                                        style = TextStyles.smallMediumNormalTextStyle
                                )
                                Text(
                                        text = "  ${data.itemCode} / ${data.qty}",
                                        style = TextStyles.smallMediumNormalTextStyle

                                )
                        }
                        Row(
                                modifier = Modifier
                                        .padding(16.dp,0.dp)
                                        .height(24.dp)
                        ){
                                Text(
                                        text = "Batch",
                                        style = TextStyles.smallMediumNormalTextStyle,
                                        modifier = Modifier
                                                .width(100.dp)

                                )
                                Text(
                                        text = ":",
                                        style = TextStyles.smallMediumNormalTextStyle
                                )
                                Text(
                                        text = "  ${data.batchNumber}",
                                        style = TextStyles.smallMediumNormalTextStyle

                                )

                        }


                        Row(
                                modifier = Modifier
                                        .padding(16.dp,8.dp)
                        ){
                                DashedLine()
                        }

                        Row(
                                modifier = Modifier
                                        .padding(16.dp,0.dp,0.dp,0.dp)

                        ){
                                Text(
                                        text = "  ${data.itemDescription}",
                                        style = TextStyles.smallLightColoredNormalTextStyle,
                                        modifier = Modifier
                                                .width(200.dp)

                                )
                        }
                }


        }
}


@Composable
fun DashedLine() {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        Canvas(
                Modifier
                        .fillMaxWidth()
                        .height(2.dp)) {

                drawLine(
                        color = Color(0xFFF99091) ,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        pathEffect = pathEffect
                )
        }

}
