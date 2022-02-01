package mx.com.rlr.countersdevchallenge.presentation.common.extension.android

import android.app.Activity
import android.content.Context
import android.content.Intent

fun <T> Context.navigateTo(javaClass: Class<T>, clearTop: Boolean = false) {
    val intent = Intent(this, javaClass).apply {
        if (clearTop)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    startActivity(intent)
    (this as Activity).finish()
}