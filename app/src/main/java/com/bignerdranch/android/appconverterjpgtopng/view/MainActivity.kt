package com.bignerdranch.android.appconverterjpgtopng.view

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.appconverterjpgtopng.App.Navigator.navigatorHolder
import com.bignerdranch.android.appconverterjpgtopng.App.Navigator.router
import com.bignerdranch.android.appconverterjpgtopng.R
import com.bignerdranch.android.appconverterjpgtopng.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)


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