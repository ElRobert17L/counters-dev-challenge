package mx.com.rlr.countersdevchallenge.presentation.common.extension.android

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(message: String) {
    Snackbar.make(this.view ?: return, message, Snackbar.LENGTH_LONG).show()
}