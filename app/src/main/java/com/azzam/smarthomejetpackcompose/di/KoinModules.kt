package com.azzam.smarthomejetpackcompose.di

import com.azzam.smarthomejetpackcompose.presentation.homepage.HomepageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomepageViewModel() }
}