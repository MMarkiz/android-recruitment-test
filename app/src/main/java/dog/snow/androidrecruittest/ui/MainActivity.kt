package dog.snow.androidrecruittest.ui

import android.os.Bundle
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.base.AndroidRecruitTestActivity

/**
 * author marcinm on 2019-05-14.
 */
class MainActivity : AndroidRecruitTestActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_fragment_only)
		if (savedInstanceState == null)
			addFragment(MainFragment(), FRAGMENT_CONTAINER)
	}
}