package dog.snow.androidrecruittest.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import dog.snow.androidrecruittest.R


/**
 * author marcinm on 2019-05-14.
 */
class LoadingDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity!!)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        return dialog
    }

    override fun onPause() {
        super.onPause()
        this.dismissAllowingStateLoss()
    }
}