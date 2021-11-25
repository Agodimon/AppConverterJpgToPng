package com.bignerdranch.android.appconverterjpgtopng.unity

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

object Navigator {

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
}