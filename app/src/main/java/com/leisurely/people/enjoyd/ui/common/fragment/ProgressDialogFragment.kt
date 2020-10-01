package com.leisurely.people.enjoyd.ui.common.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentProgressDialogBinding
import com.leisurely.people.enjoyd.ui.base.BaseDialogFragment


class ProgressDialogFragment :
    BaseDialogFragment<FragmentProgressDialogBinding>(R.layout.fragment_progress_dialog) {

    override fun onResume() {
        super.onResume()
        dialog?.let { dialog ->
            dialog.setCanceledOnTouchOutside(false)
            isCancelable = false
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }

    companion object {
        fun newInstance(): ProgressDialogFragment = ProgressDialogFragment()
    }
}