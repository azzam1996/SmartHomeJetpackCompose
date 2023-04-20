package com.azzam.smarthomejetpackcompose.navigation

sealed class Screen(val route: String) {
    object HomepageScreen: Screen("homepage_screen")
    object RoomScreen: Screen("room_screen")

    fun withArgs(vararg args: String?): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}