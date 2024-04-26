package com.friendship.bhaibhaiclinic.base


import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.friendship.bhaibhaiclinic.databinding.LayoutLoadingDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class LoadingDialog ( context: Context) {
    private var dialog: AlertDialog
    init {
        MaterialAlertDialogBuilder(context).apply {
            background = ContextCompat.getDrawable(context, android.R.color.transparent)
            dialog = create().apply {
                setView(LayoutLoadingDialogBinding.inflate(LayoutInflater.from(context), null, false).root)
            }
        }
    }
    fun show() {
        if (!dialog.isShowing) dialog.show()
    }
    fun dismiss() {
        if (dialog.isShowing) dialog.dismiss()
    }
}