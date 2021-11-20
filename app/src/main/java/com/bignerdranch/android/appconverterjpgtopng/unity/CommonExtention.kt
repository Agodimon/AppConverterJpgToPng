package com.bignerdranch.android.appconverterjpgtopng.unity

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Context.toast(msg: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Fragment.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(resId, duration)
}

fun Fragment.toast(msg: String?, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(msg, duration)
}

