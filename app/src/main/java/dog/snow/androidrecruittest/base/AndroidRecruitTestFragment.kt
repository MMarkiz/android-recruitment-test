package dog.snow.androidrecruittest.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import dog.snow.androidrecruittest.ui.dialog.LoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * author marcinm on 2019-05-14.
 */
abstract class AndroidRecruitTestFragment : Fragment(),CoroutineScope{


    private lateinit var loadingDialog: LoadingDialog

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun showLoadingDialog(show: Boolean) {
        if (show) {
            loadingDialog = LoadingDialog()
            loadingDialog.setTargetFragment(this, 0)
            loadingDialog.show(fragmentManager!!, null)
        } else if (::loadingDialog.isInitialized) {
            loadingDialog.dismiss()
        }

    }
}