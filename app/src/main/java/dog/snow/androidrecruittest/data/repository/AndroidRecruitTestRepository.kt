package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.db.Item
import dog.snow.androidrecruittest.data.model.NetworkState
import dog.snow.androidrecruittest.internal.Event

/**
 * author marcinm on 2019-05-14.
 */
interface AndroidRecruitTestRepository {
	suspend fun getItems(): LiveData<List<Item>>
	fun getNetworkState(): LiveData<Event<NetworkState>>
	suspend fun fetchItems()
}