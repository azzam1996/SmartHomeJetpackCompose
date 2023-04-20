package com.azzam.smarthomejetpackcompose.presentation.homepage

import androidx.lifecycle.ViewModel
import com.azzam.smarthomejetpackcompose.R
import com.azzam.smarthomejetpackcompose.domain.model.Device
import com.azzam.smarthomejetpackcompose.domain.model.Room

class HomepageViewModel : ViewModel() {
    val list = mutableListOf<Room>()

    init {
        initHardCodedList()
    }

    private fun initHardCodedList() {
        val devices1 = mutableListOf<Device>()
        devices1.add(
            Device(
                title = "Light",
                image = R.drawable.ic_light
            )
        )
        devices1.add(
            Device(
                title = "Smart TV",
                image = R.drawable.ic_tv
            )
        )
        devices1.add(
            Device(
                title = "Fridge",
                image = R.drawable.ic_kitchen
            )
        )


        val devices2 = mutableListOf<Device>()
        devices2.add(
            Device(
                title = "Couch",
                image = R.drawable.ic_living_room
            )
        )
        devices2.add(
            Device(
                title = "Light",
                image = R.drawable.ic_light
            )
        )
        devices2.add(
            Device(
                title = "Smart TV",
                image = R.drawable.ic_tv
            )
        )
        devices2.add(
            Device(
                title = "Fridge",
                image = R.drawable.ic_kitchen
            )
        )

        val devices3 = mutableListOf<Device>()
        devices3.add(
            Device(
                title = "Light",
                image = R.drawable.ic_light
            )
        )
        devices3.add(
            Device(
                title = "Smart TV",
                image = R.drawable.ic_tv
            )
        )

        list.clear()
        list.add(
            Room(
                title = "Living Room",
                image = R.drawable.ic_living_room,
                devices = devices2,
                temperature = 24,
                maxTemperature = 30,
                minTemperature = 10
            )
        )

        list.add(
            Room(
                title = "Kitchen",
                image = R.drawable.ic_kitchen,
                devices = devices1,
                temperature = 14,
                maxTemperature = 20,
                minTemperature = 10
            )
        )

        list.add(
            Room(
                title = "Study Room",
                image = R.drawable.ic_study_room,
                devices = devices3,
                temperature = 714,
                maxTemperature = 1000,
                minTemperature = 120
            )
        )

        list.add(
            Room(
                title = "Garage",
                image = R.drawable.ic_garage,
                devices = devices1,
                temperature = 33,
                maxTemperature = 50,
                minTemperature = 10
            )
        )

        list.add(
            Room(
                title = "Bathroom",
                image = R.drawable.ic_bathroom,
                devices = devices2,
                temperature = 240,
                maxTemperature = 250,
                minTemperature = 10
            )
        )

        list.add(
            Room(
                title = "Dining Room",
                image = R.drawable.ic_dining_room,
                devices = devices1,
                temperature = 4,
                maxTemperature = 30,
                minTemperature = 10
            )
        )
    }
}