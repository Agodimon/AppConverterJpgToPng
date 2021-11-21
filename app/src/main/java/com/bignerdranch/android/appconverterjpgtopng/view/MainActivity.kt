package com.bignerdranch.android.appconverterjpgtopng.view

import android.os.Bundle
import com.bignerdranch.android.appconverterjpgtopng.R
import com.bignerdranch.android.appconverterjpgtopng.unity.Navigator.navigatorHolder
import com.bignerdranch.android.appconverterjpgtopng.unity.Navigator.router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(R.layout.activity_main) {

    private val navigator = AppNavigator(this, android.R.id.content)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(ConverterScreen())
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}