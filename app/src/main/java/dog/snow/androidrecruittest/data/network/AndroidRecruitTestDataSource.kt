package dog.snow.androidrecruittest.data.network

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.db.Item
import dog.snow.androidrecruittest.data.model.NetworkState
import dog.snow.androidrecruittest.internal.Event

/**
 * author marcinm on 2019-05-14.
 */
interface AndroidRecruitTestDataSource {
	val networkState: LiveData<Event<NetworkState>>
	val downloadItems: LiveData<ArrayList<Item>>

	suspend fun fetchItems()
}