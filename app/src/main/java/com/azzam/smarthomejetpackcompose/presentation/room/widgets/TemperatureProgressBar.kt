package com.azzam.smarthomejetpackcompose.presentation.room.widgets

import android.graphics.BitmapFactory
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.azzam.smarthomejetpackcompose.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin
import com.azzam.smarthomejetpackcompose.R

@Composable
fun TemperatureProgressBar(
    percentage: Float,
    degree: Int,
    modifier: Modifier = Modifier

) {
    val context = LocalContext.current

    var played by remember {
        mutableStateOf(false)
    }

    val sweepAngle by animateFloatAsState(
        targetValue = if (played) percentage * 360 else 0f,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 500
        )
    )

    val degreeAnimation by animateIntAsState(
        targetValue = if (played) degree else 0,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 500
        )
    )

    LaunchedEffect(key1 = true) {
        played = true
    }

    ConstraintLayout(modifier = modifier) {
        val (tvDegree, tvCelsius, tvTemp) = createRefs()
        Canvas(
            modifier = Modifier
                .size(300.dp)
        ) {

            // ======================    drawMainCircle   =====================

            val colors = listOf<Color>(
                Yellow,
                WildStrawberry,
                PinkFlamingo,
                PinkFlamingo,
                Turbo,
                Yellow,
            )

            val sweepGradient = Brush.sweepGradient(
                colors = colors,
                center = this.center
            )

            drawArc(
                brush = sweepGradient,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(30.dp.toPx(), 30.dp.toPx()),
                style = Stroke(20.dp.toPx()),
                size = Size(240.dp.toPx(), 240.dp.toPx())
            )

            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_blur)
            drawImage(
                bitmap.asImageBitmap(),
                dstOffset = IntOffset(-30.dp.toPx().toInt(), -30.dp.toPx().toInt()),
                dstSize = IntSize(355.dp.toPx().toInt(), 355.dp.toPx().toInt())
            )

            // ======================    drawProgressArc   =====================

            val progressStroke = Stroke(
                width = 5f,
                cap = StrokeCap.Round
            )

            drawArc(
                color = Color.White,
                startAngle = 90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(30.dp.toPx(), 30.dp.toPx()),
                style = progressStroke,
                size = Size(240.dp.toPx(), 240.dp.toPx())
            )

            // ======================    drawProgressIndicator   =====================

            val endX: Double =
                cos(Math.toRadians((sweepAngle + 90).toDouble())) * (this.size.width / 2 - 30.dp.toPx()) + this.size.width / 2

            val endY: Double =
                sin(Math.toRadians((sweepAngle + 90).toDouble())) * (this.size.width / 2 - 30.dp.toPx()) + this.size.height / 2


            drawCircle(
                color = getOuterCircleColor(sweepAngle),
                radius = 18.dp.toPx(),
                center = Offset(endX.toFloat(), endY.toFloat()),
            )

            drawCircle(
                color = Color.White,
                radius = 12.dp.toPx(),
                center = Offset(endX.toFloat(), endY.toFloat()),
            )
        }

        Text(
            text = "$degreeAnimation°",
            fontSize = 42.sp,
            fontFamily = nunitoFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .constrainAs(tvDegree) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "temp",
            fontSize = 17.sp,
            fontFamily = nunitoFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .constrainAs(tvTemp) {
                    bottom.linkTo(tvDegree.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "celsius",
            fontSize = 17.sp,
            fontFamily = nunitoFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .constrainAs(tvCelsius) {
                    top.linkTo(tvDegree.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}


private fun getOuterCircleColor(angle: Float): Color {
    return when (angle % 360f) {
        in 0.0..25.0 -> Pomegranate
        in 25.0..150.0 -> BrilliantRose
        in 150.0..190.0 -> Pizazz
        in 340.0..360.0 -> Pomegranate
        else -> Koromiko
    }
}