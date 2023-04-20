package com.azzam.smarthomejetpackcompose.presentation.room

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.*
import com.azzam.smarthomejetpackcompose.R
import com.azzam.smarthomejetpackcompose.domain.model.Room
import com.azzam.smarthomejetpackcompose.presentation.room.widgets.DeviceItem
import com.azzam.smarthomejetpackcompose.presentation.room.widgets.DeviceSelection
import com.azzam.smarthomejetpackcompose.presentation.room.widgets.TemperatureProgressBar
import com.azzam.smarthomejetpackcompose.ui.theme.*


@Composable
fun RoomScreen(
    room: Room?
) {
    val scrollState = rememberScrollState()
    val percentage = (room?.temperature?.toFloat() ?: 1f) / (room?.maxTemperature?.toFloat() ?: 1f)

    var checked by remember {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (ivBack, tvTitle, column) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp, 17.dp)
                .constrainAs(ivBack) {
                    top.linkTo(parent.top, 34.dp)
                    start.linkTo(parent.start, 36.dp)
                }
        )

        Text(
            text = room?.title ?: "",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = nunitoFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(tvTitle) {
                    top.linkTo(ivBack.top)
                    bottom.linkTo(ivBack.bottom)
                    start.linkTo(parent.start, 65.dp)
                    end.linkTo(parent.end, 65.dp)
                }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .constrainAs(column) {
                    top.linkTo(tvTitle.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (temperatureProgressBar, clSwitch, tvMinTemperature, tvMaxTemperature, tabs, tvDevices, devices) = createRefs()

                TemperatureProgressBar(
                    percentage = percentage,
                    degree = room?.temperature ?: 0,
                    modifier = Modifier
                        .constrainAs(temperatureProgressBar) {
                            top.linkTo(parent.top, 30.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = "${room?.minTemperature}°",
                    fontSize = 20.sp,
                    fontFamily = nunitoFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(tvMinTemperature) {
                            start.linkTo(temperatureProgressBar.start)
                            top.linkTo(temperatureProgressBar.bottom)
                        }
                )

                Text(
                    text = "${room?.maxTemperature}°",
                    fontSize = 20.sp,
                    fontFamily = nunitoFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(tvMaxTemperature) {
                            end.linkTo(temperatureProgressBar.end)
                            top.linkTo(temperatureProgressBar.bottom)
                        }
                )

                ConstraintLayout(
                    modifier = Modifier
                        .height(25.dp)
                        .width(46.dp)
                        .clip(RoundedCornerShape(36.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Valentino, VividViolet),
                                start = Offset(0f, Float.POSITIVE_INFINITY),
                                end = Offset(Float.POSITIVE_INFINITY, 0f)
                            )
                        )
                        .constrainAs(clSwitch) {
                            top.linkTo(temperatureProgressBar.top)
                            end.linkTo(temperatureProgressBar.end)
                        }
                ) {
                    val (switch) = createRefs()

                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        },
                        thumbContent = {
                            if (checked) {
                                ConstraintLayout(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(
                                            brush = Brush.horizontalGradient(
                                                colors = listOf(Mustard, BrinkPink)
                                            )
                                        )
                                ) {

                                }
                            } else {
                                ConstraintLayout(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color.White)
                                ) {

                                }
                            }
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Color.Transparent,
                            checkedBorderColor = Color.Transparent,
                            uncheckedTrackColor = Color.Transparent,
                            uncheckedBorderColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .constrainAs(switch) {
                                top.linkTo(parent.top)
                            }
                    )
                }

                DeviceSelection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(36.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Valentino, VividViolet),
                                start = Offset(0f, Float.POSITIVE_INFINITY),
                                end = Offset(Float.POSITIVE_INFINITY, 0f)
                            )
                        )
                        .padding(2.dp)
                        .constrainAs(tabs) {
                            top.linkTo(tvMaxTemperature.bottom, 30.dp)
                            start.linkTo(parent.start, 40.dp)
                            end.linkTo(parent.end, 40.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = "Devices",
                    fontSize = 20.sp,
                    fontFamily = nunitoFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(tvDevices) {
                            start.linkTo(parent.start, 36.dp)
                            top.linkTo(tabs.bottom, 30.dp)
                        }
                )

                LazyRow(
                    modifier = Modifier
                        .constrainAs(devices) {
                            top.linkTo(tvDevices.bottom, 20.dp)
                            start.linkTo(parent.start, 26.dp)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                ) {
                    room?.devices?.let { list ->
                        itemsIndexed(list) { _, item ->
                            DeviceItem(device = item) {

                            }
                        }
                    }
                }
            }
        }
    }
}