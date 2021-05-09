package com.rocksolidapps.movies.ext

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("imageUri")
fun SimpleDraweeView.imageUri(imageUri: String?) {
    if (imageUri == null) {
        setActualImageResource(0)
    } else {
        setImageURI(imageUri)
    }
}