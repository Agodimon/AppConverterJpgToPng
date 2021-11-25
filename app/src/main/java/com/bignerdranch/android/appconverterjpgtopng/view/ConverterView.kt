package com.bignerdranch.android.appconverterjpgtopng.view

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ConverterView:MvpView {

    @StateStrategyType(SingleStateStrategy::class)
    fun showSuccess(path: String)

    @StateStrategyType(SingleStateStrategy::class)
    fun pickImage()

    @StateStrategyType(SingleStateStrategy::class)
    fun showError(@StringRes msg: Int)
}