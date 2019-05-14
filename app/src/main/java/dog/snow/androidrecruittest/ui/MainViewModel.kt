package dog.snow.androidrecruittest.ui

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dog.snow.androidrecruittest.data.repository.AndroidRecruitTestRepository
import dog.snow.androidrecruittest.internal.lazyDeferred

/**
 * author marcinm on 2019-05-14.
 */
class MainViewModel(private val androidRecruitTestRepository: AndroidRecruitTestRepository) : ViewModel() {

    val items by lazyDeferred { androidRecruitTestRepository.getItems() }

    val networkState = Transformations.map(androidRecruitTestRepository.getNetworkState()) { it }

    public suspend fun fetchItems(){
        androidRecruitTestRepository.fetchItems()
    }
}