package dog.snow.androidrecruittest.data.repository

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.db.Item
import dog.snow.androidrecruittest.data.db.ItemDao
import dog.snow.androidrecruittest.data.model.NetworkState
import dog.snow.androidrecruittest.data.network.AndroidRecruitTestDataSource
import dog.snow.androidrecruittest.internal.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author marcinm on 2019-05-14.
 */
class AndroidRecruitTestRepositoryImpl(
	private val androidNetworkDataSource: AndroidRecruitTestDataSource,
	private val itemDao: ItemDao
) : AndroidRecruitTestRepository {


	init {
		androidNetworkDataSource.apply {
			downloadItems.observeForever { persistItems(it) }
		}
	}

	override fun getNetworkState(): LiveData<Event<NetworkState>> {
		return androidNetworkDataSource.networkState
	}

	override suspend fun getItems(): LiveData<List<Item>> {
		return withContext(Dispatchers.IO) {
			return@withContext itemDao.getItems()
		}
	}

	override suspend fun fetchItems() {
		androidNetworkDataSource.fetchItems()
	}


	private fun persistItems(items: ArrayList<Item>) {
		GlobalScope.launch(Dispatchers.IO) {
			itemDao.insertItemsList(items)
		}
	}
}