package com.example.testtask.presentation.databinding.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageByUrl")
fun setUrlImageToView(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}