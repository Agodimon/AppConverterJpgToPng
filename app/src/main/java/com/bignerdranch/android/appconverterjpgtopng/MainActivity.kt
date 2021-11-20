package com.bignerdranch.android.appconverterjpgtopng

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.appconverterjpgtopng.databinding.ActivityMainBinding
import com.bignerdranch.android.appconverterjpgtopng.databinding.ActivityMainBinding.bind
import java.io.ByteArrayOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the bitmap from assets and display into image view
        val bitmap = assetsToBitmap("tulip.jpg")
        // If bitmap is not null
        bitmap?.let {
            binding.imageViewBitmap.setImageBitmap(bitmap)
        }


        // Click listener for button widget
        binding.button.setOnClickListener{
            if(bitmap!=null){
                // Compress bitmap and convert image format from one to another
                val compressedBitmap = bitmap.compress(Bitmap.CompressFormat.PNG)
                //val compressedBitmap = bitmap.compress(Bitmap.CompressFormat.WEBP)

                //val compressedBitmap = bitmap.compress(Bitmap.CompressFormat.JPEG)
                //val compressedBitmap = bitmap.compress(Bitmap.CompressFormat.JPEG, 10)
                //val compressedBitmap = bitmap.compress(quality = 10) // Compress only


                // Display the compressed bitmap into image view
                binding.imageViewCompressed.setImageBitmap(compressedBitmap)

                // Notify user
                binding.textView.text = getString(R.string.text)
            }else{
                // Log error
            }
        }
    }


    // Method to get a bitmap from assets
    private fun assetsToBitmap(fileName:String): Bitmap?{
        return try{
            val stream = assets.open(fileName)
            BitmapFactory.decodeStream(stream)
        }catch (e: IOException){
            e.printStackTrace()
            null
        }
    }
}


// Extension function to compress and change bitmap image format programmatically
fun Bitmap.compress(format:Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, quality:Int = 100):Bitmap{
    // Initialize a new ByteArrayStream
    val stream = ByteArrayOutputStream()

    /*
        **** reference source developer.android.com ***

        public boolean compress (Bitmap.CompressFormat format, int quality, OutputStream stream)
            Write a compressed version of the bitmap to the specified outputstream.
            If this returns true, the bitmap can be reconstructed by passing a
            corresponding inputstream to BitmapFactory.decodeStream().

            Note: not all Formats support all bitmap configs directly, so it is possible
            that the returned bitmap from BitmapFactory could be in a different bitdepth,
            and/or may have lost per-pixel alpha (e.g. JPEG only supports opaque pixels).

            Parameters
            format : The format of the compressed image
            quality : Hint to the compressor, 0-100. 0 meaning compress for small size,
                100 meaning compress for max quality. Some formats,
                like PNG which is lossless, will ignore the quality setting
            stream: The outputstream to write the compressed data.

            Returns
                true if successfully compressed to the specified stream.


        Bitmap.CompressFormat
            Specifies the known formats a bitmap can be compressed into.

                Bitmap.CompressFormat  JPEG
                Bitmap.CompressFormat  PNG
                Bitmap.CompressFormat  WEBP
    */

    // Compress the bitmap with JPEG format and quality 50%
    this.compress(
        format,
        quality,
        stream
    )

    val byteArray = stream.toByteArray()

    // Finally, return the compressed bitmap
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}