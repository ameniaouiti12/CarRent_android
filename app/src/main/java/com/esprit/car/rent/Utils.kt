package com.esprit.car.rent

import android.content.Context
import android.content.DialogInterface

fun Context.alertDialog(
    title: String,
    message: String,
    positiveBtnText: String? = null,
    negativeBtnText: String? = null,
    neutralBtnText: String? = null,
    positiveBtnClickListener: DialogInterface.OnClickListener? = null,
    neutralBtnClickListener: DialogInterface.OnClickListener? = null,
    negativeBtnClickListener: DialogInterface.OnClickListener? = null,
    cancelListener: DialogInterface.OnCancelListener? = null,
    dismissListener: DialogInterface.OnDismissListener? = null
) : android.app.AlertDialog.Builder{
    val builder = android.app.AlertDialog.Builder(this, R.style.SimpleElegantAlertDialogTheme)
    builder.setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveBtnText, positiveBtnClickListener)
        .setNegativeButton(negativeBtnText, negativeBtnClickListener)
        .setNeutralButton(neutralBtnText,neutralBtnClickListener)
        .create()

    cancelListener?.let { builder.setOnCancelListener(it) }
    dismissListener?.let { builder.setOnDismissListener(it) }


    return builder
}