package dog.snow.androidrecruittest.ui.base

import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import dog.snow.androidrecruittest.R

/**
 * author marcinm on 2019-05-14.
 */
abstract class AndroidRecruitTestActivity : AppCompatActivity() {

	companion object {
		const val FRAGMENT_CONTAINER = R.id.fragment_container
	}

	private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
		beginTransaction().func().commit()
	}

	protected fun addFragment(fragment: Fragment, frameId: Int) {
		supportFragmentManager.inTransaction { add(frameId, fragment) }
	}

	fun showSnackMessage(@StringRes message: Int) {
		Snackbar.make(getRootView(), message, Snackbar.LENGTH_LONG).show()
	}

	private fun getRootView(): ViewGroup {
		return this.findViewById(android.R.id.content) as ViewGroup
	}
}