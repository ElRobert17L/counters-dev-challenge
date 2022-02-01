package mx.com.rlr.countersdevchallenge.presentation.common.extension.android

import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(message: String) {
    Snackbar.make(this.view ?: return, message, Snackbar.LENGTH_LONG).show()
}

fun Fragment.showMaterialDialog(
    title: String,
    message: String,
    positiveText: String,
    negativeText: String,
    action: ((dialog: DialogInterface, which: Int) -> Unit)?
) {
    MaterialAlertDialogBuilder(requireContext())
        .setCancelable(false)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveText, action)
        .setNegativeButton(negativeText, null)
        .show()
}