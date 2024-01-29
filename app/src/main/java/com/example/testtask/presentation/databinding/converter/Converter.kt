package com.example.testtask.presentation.databinding.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory

object Converter {
    @JvmStatic
    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.count())
    }
}