package com.azzam.smarthomejetpackcompose.presentation.room.widgets

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.*
import com.azzam.smarthomejetpackcompose.R
import com.azzam.smarthomejetpackcompose.ui.theme.BrinkPink
import com.azzam.smarthomejetpackcompose.ui.theme.Mustard
import com.azzam.smarthomejetpackcompose.ui.theme.nunitoFamily

@OptIn(ExperimentalMotionApi::class)
@Composable
fun DeviceSelection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val interactionSource = remember { MutableInteractionSource() }

    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    var currentProgress by remember {
        mutableStateOf(0f)
    }

    var nextProgress by remember {
        mutableStateOf(0f)
    }

    var isPlayed by remember {
        mutableStateOf(false)
    }

    val animation = animateFloatAsState(
        targetValue = if (isPlayed) nextProgress else currentProgress,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 0
        )
    )

    ConstraintLayout(modifier = modifier) {

        val (clWeather, clFan, clSettings, motionLayout) = createRefs()

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = animation.value,
            modifier = Modifier
                .constrainAs(motionLayout) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(40.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Mustard, BrinkPink)
                        )
                    )
                    .width(getSelectedItemWidth(context, density))
                    .layoutId("clSelected"),
            ) {}
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp)
                .constrainAs(clWeather) {
                    start.linkTo(parent.start)
                    end.linkTo(clFan.start)
                    width = Dimension.fillToConstraints
                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    nextProgress = 0f
                    isPlayed = true
                }
        ) {
            val (image, text) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_weather),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, 7.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "TEMP",
                fontFamily = nunitoFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(image.bottom, 5.dp)
                        start.linkTo(parent.start, 3.dp)
                        end.linkTo(parent.end, 3.dp)
                    }
            )
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp)
                .constrainAs(clFan) {
                    start.linkTo(clWeather.end)
                    end.linkTo(clSettings.start)
                    width = Dimension.fillToConstraints
                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    nextProgress = 0.5f
                    isPlayed = true
                }
        ) {
            val (image, text) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_fan),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, 7.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "FAN",
                fontFamily = nunitoFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(image.bottom, 5.dp)
                        start.linkTo(parent.start, 3.dp)
                        end.linkTo(parent.end, 3.dp)
                    }
            )
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp)
                .constrainAs(clSettings) {
                    end.linkTo(parent.end)
                    start.linkTo(clFan.end)
                    width = Dimension.fillToConstraints
                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    nextProgress = 1f
                    isPlayed = true
                }
        ) {
            val (image, text) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, 7.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "MODE",
                fontFamily = nunitoFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(image.bottom, 5.dp)
                        start.linkTo(parent.start, 3.dp)
                        end.linkTo(parent.end, 3.dp)
                    }
            )
        }
    }
}

fun getSelectedItemWidth(context: Context, density: Density): Dp {
    val width = context.resources.displayMetrics.widthPixels.dp / density.density
    return (width - 80.dp) / 3
}