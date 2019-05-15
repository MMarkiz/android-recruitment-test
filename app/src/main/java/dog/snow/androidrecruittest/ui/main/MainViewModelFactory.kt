package dog.snow.androidrecruittest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.data.repository.AndroidRecruitTestRepository

/**
 * author marcinm on 2019-05-14.
 */
class MainViewModelFactory(private val androidRecruitTestRepository: AndroidRecruitTestRepository) : ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return MainViewModel(androidRecruitTestRepository) as T
	}
}