package com.inmy.products.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.inmy.products.R

class ImageSelectionDialog(context: Context) : Dialog(context) {
    val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_select_image, null)

    val mBuilder = AlertDialog.Builder(context)
        .setView(mDialogView)
        .setTitle("Select Image")

    val  mAlertDialog = mBuilder.show()



}