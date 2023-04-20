package com.azzam.smarthomejetpackcompose.presentation.room.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.azzam.smarthomejetpackcompose.domain.model.Device
import com.azzam.smarthomejetpackcompose.ui.theme.*

@Composable
fun DeviceItem(
    device: Device,
    modifier: Modifier = Modifier,
    action: (Device) -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .width(141.dp)
            .height(199.dp)
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(36.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Valentino, VividViolet),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
                .clickable {
                    action.invoke(device)
                }
        ) {
            val (ivImage, ivBg, tvTitle) = createRefs()

            ConstraintLayout(
                modifier = Modifier
                    .clip(CircleShape)
                    .alpha(0.2f)
                    .border(BorderStroke(width = 2.dp, color = Gray99), shape = CircleShape)
                    .size(56.dp)
                    .background(color = Gray43, shape = CircleShape)
                    .constrainAs(ivBg) {
                        top.linkTo(parent.top, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {}

            AsyncImage(
                model = device.image,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(ivImage) {
                        top.linkTo(ivBg.top)
                        bottom.linkTo(ivBg.bottom)
                        start.linkTo(ivBg.start)
                        end.linkTo(ivBg.end)
                    }
            )

            Text(
                text = device?.title.toString(),
                fontFamily = nunitoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 19.sp,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                maxLines = 2,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .constrainAs(tvTitle) {
                        start.linkTo(parent.start, 5.dp)
                        end.linkTo(parent.end, 5.dp)
                        top.linkTo(ivBg.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}