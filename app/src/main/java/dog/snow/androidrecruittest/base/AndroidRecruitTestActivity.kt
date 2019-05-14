package dog.snow.androidrecruittest.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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
}