package com.bignerdranch.android.appconverterjpgtopng.model.converter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import io.reactivex.rxjava3.core.Single
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File

class ConverterRepositoryImpl (private val context: Context) : ConverterRepository{


    override fun convert(uri: Uri): Single<String> {

        return Single
            .just(uri)
            .map {
                val file = File(
                    context.getExternalFilesDir(
                        Environment.DIRECTORY_PICTURES
                    ), "$fileName.$fileExt"
                )
                file.createNewFile()

                BufferedOutputStream(file.outputStream()).use { fos ->
                    BufferedInputStream(
                        context.contentResolver.openInputStream(
                            it
                        )
                    ).use { inputStream ->
                        val bitmap =
                            BitmapFactory.decodeStream(inputStream)

                        bitmap.compress(
                            Bitmap.CompressFormat.PNG,
                            100,
                            fos
                        )
                    }
                }

                file.absolutePath
            }
    }

    companion object {
        private const val fileName = "convert_image"
        private const val fileExt = "png"
    }
}