package dog.snow.androidrecruittest.ui.main

import android.os.Bundle
import android.os.Handler
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.base.AndroidRecruitTestActivity

/**
 * author marcinm on 2019-05-14.
 */
class MainActivity : AndroidRecruitTestActivity() {

	companion object {
		const val DELAY_TWO_SECONDS = 2000L
	}
	private var backPressedOnce = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_fragment_only)
		if (savedInstanceState == null)
			addFragment(MainFragment(), FRAGMENT_CONTAINER)
	}

	override fun onBackPressed() {
		if (!backPressedOnce) {
			this.backPressedOnce = true
			showSnackMessage(R.string.main_exit_click_info)
			Handler().postDelayed({ backPressedOnce = false }, DELAY_TWO_SECONDS)
		} else {
			super.onBackPressed()
		}
	}
}