package com.azzam.smarthomejetpackcompose.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.azzam.smarthomejetpackcompose.domain.model.Room
import com.azzam.smarthomejetpackcompose.presentation.homepage.HomepageScreen
import com.azzam.smarthomejetpackcompose.presentation.room.RoomScreen
import com.google.gson.Gson


@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.HomepageScreen.route) {
        composable(Screen.HomepageScreen.route) {
            HomepageScreen(navHostController)
        }
        composable(
            Screen.RoomScreen.route + "/{room}",
            arguments = listOf(
                navArgument("room") {
                    type = RoomParamType()
                })
        ) {

            val room = it.arguments?.getParcelable<Room>("room")
            RoomScreen(room = room)
        }
    }
}


class RoomParamType : NavType<Room>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Room? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Room {
        return Gson().fromJson(value, Room::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Room) {
        bundle.putParcelable(key, value)
    }
}