package com.inmy.products.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.inmy.products.R

class DefaultAlertDialog (context: Context, title: String) : Dialog(context) {
    val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_alert_default, null)

    val mBuilder = AlertDialog.Builder(context)
        .setView(mDialogView)
        .setTitle(title)

    val  mAlertDialog = mBuilder.show()


}