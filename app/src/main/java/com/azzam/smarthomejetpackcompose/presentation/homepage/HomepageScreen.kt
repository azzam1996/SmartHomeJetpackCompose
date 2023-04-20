package com.azzam.smarthomejetpackcompose.presentation.homepage

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.azzam.smarthomejetpackcompose.R
import com.azzam.smarthomejetpackcompose.navigation.Screen
import com.azzam.smarthomejetpackcompose.presentation.homepage.widgets.RoomItem
import com.azzam.smarthomejetpackcompose.ui.theme.*
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@Composable
fun HomepageScreen(
    navHostController: NavHostController,
    viewModel: HomepageViewModel = getViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (ivHamburger, ivPhoto, tvGreeting, tvWelcome, clTitle, rooms) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_hamburger),
            contentDescription = null,
            modifier = Modifier
                .width(24.dp)
                .height(16.dp)
                .constrainAs(ivHamburger) {
                    top.linkTo(parent.top, 34.dp)
                    start.linkTo(parent.start, 36.dp)
                }
        )

        ConstraintLayout(
            modifier = Modifier
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Mustard, BrinkPink)
                    )
                )
                .size(48.dp)
                .constrainAs(ivPhoto) {
                    end.linkTo(parent.end, 36.dp)
                    top.linkTo(parent.top, 18.dp)
                }
        ) {}

        Text(
            text = "Hi Dee",
            fontFamily = nunitoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier
                .constrainAs(tvGreeting) {
                    top.linkTo(ivHamburger.bottom, 30.dp)
                    start.linkTo(parent.start, 36.dp)
                    end.linkTo(parent.end, 36.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                    append("Welcome to ")
                }
                append("Dee Home")
            },
            fontFamily = nunitoFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier
                .constrainAs(tvWelcome) {
                    top.linkTo(tvGreeting.bottom, 5.dp)
                    start.linkTo(parent.start, 36.dp)
                    end.linkTo(parent.end, 36.dp)
                    width = Dimension.fillToConstraints
                }
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, end = 36.dp)
                .constrainAs(clTitle) {
                    top.linkTo(tvWelcome.bottom, 48.dp)
                }
        ) {
            val (tvRoom, clAdd) = createRefs()

            Text(
                text = "Your Rooms",
                fontFamily = nunitoFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(tvRoom) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(clAdd.start, 5.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            ConstraintLayout(
                modifier = Modifier
                    .clip(RoundedCornerShape(36.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Mustard, BrinkPink)
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .constrainAs(clAdd) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            ) {

                val (tvAdd, ivPlus) = createRefs()
                Text(
                    text = "Add",
                    fontFamily = nunitoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(tvAdd) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(ivPlus.start, 22.dp)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier
                        .size(17.dp)
                        .constrainAs(ivPlus) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        })

            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
                .constrainAs(rooms) {
                    top.linkTo(clTitle.bottom, 30.dp)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        ) {
            itemsIndexed(viewModel.list) { _, item ->
                RoomItem(room = item) {
                    val json = Uri.encode(Gson().toJson(item))
                    navHostController.navigate(Screen.RoomScreen.route + "/$json")
                }
            }
        }
    }
}