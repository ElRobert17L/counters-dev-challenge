package mx.com.rlr.countersdevchallenge.presentation.common.extension.android

import android.view.View

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}