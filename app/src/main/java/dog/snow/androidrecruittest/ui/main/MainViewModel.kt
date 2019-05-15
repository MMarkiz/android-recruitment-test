package dog.snow.androidrecruittest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.repository.AndroidRecruitTestRepository
import dog.snow.androidrecruittest.internal.lazyDeferred

/**
 * author marcinm on 2019-05-14.
 */
class MainViewModel(private val androidRecruitTestRepository: AndroidRecruitTestRepository) : ViewModel() {

	val items by lazyDeferred { androidRecruitTestRepository.getItems() }

	val phrase = MutableLiveData<String>()

	val networkState = Transformations.map(androidRecruitTestRepository.getNetworkState()) { it }

	suspend fun fetchItems() {
		androidRecruitTestRepository.fetchItems()
	}
}