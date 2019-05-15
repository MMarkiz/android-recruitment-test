package dog.snow.androidrecruittest.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dog.snow.androidrecruittest.data.db.Item
import dog.snow.androidrecruittest.data.model.NetworkState
import dog.snow.androidrecruittest.internal.Event
import dog.snow.androidrecruittest.internal.NoConnectivityException

/**
 * author marcinm on 2019-05-14.
 */
class AndroidRecruitTestDataSourceImpl(private val androidRecruitTestApiService: AndroidRecruitTestApiService) :
	AndroidRecruitTestDataSource {

	companion object {
		private const val CODE_NOT_FOUND = 404
	}

	private val _networkState = MutableLiveData<Event<NetworkState>>()
	override val networkState: LiveData<Event<NetworkState>>
		get() = _networkState

	private val _downloadItems = MutableLiveData<ArrayList<Item>>()
	override val downloadItems: LiveData<ArrayList<Item>>
		get() = _downloadItems


	override suspend fun fetchItems() {
		_networkState.postValue(Event(NetworkState.LOADING))
		try {
			val response = androidRecruitTestApiService.getItemsAsync().await()
			when {
				response.isSuccessful -> {
					_downloadItems.postValue(response.body())
					_networkState.postValue(Event(NetworkState.SUCCESS))
				}
				response.code() == CODE_NOT_FOUND -> _networkState.postValue(Event(NetworkState.ERROR_NOT_FOUND))
				else -> _networkState.postValue(Event(NetworkState.ERROR_UNKNOWN))
			}
		} catch (e: NoConnectivityException) {
			_networkState.postValue(Event(NetworkState.NO_INTERNET_CONNECTION))
		}
	}
}