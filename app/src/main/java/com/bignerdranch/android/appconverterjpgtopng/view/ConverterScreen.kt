package com.bignerdranch.android.appconverterjpgtopng.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ConverterScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ConverterFragment.newInstance()
}