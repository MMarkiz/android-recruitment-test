package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.base.AndroidRecruitTestFragment

/**
 * author marcinm on 2019-05-14.
 */
class MainFragment : AndroidRecruitTestFragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_main, container, false)
	}
}