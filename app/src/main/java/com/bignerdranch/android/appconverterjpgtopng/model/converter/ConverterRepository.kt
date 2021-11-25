package com.bignerdranch.android.appconverterjpgtopng.model.converter

import android.net.Uri
import io.reactivex.rxjava3.core.Single

interface ConverterRepository {
    fun convert(uri: Uri): Single<String>
}