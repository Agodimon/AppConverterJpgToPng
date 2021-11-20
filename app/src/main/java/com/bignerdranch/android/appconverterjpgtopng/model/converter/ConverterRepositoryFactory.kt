package com.bignerdranch.android.appconverterjpgtopng.model.converter

import android.content.Context

object ConverterRepositoryFactory {
    fun create(context: Context): ConverterRepository =
        ConverterRepositoryImpl(context = context)
}