package dog.snow.androidrecruittest.data.model

/**
 * author marcinm on 2019-05-14.
 */
open class NetworkState(val state: State) {

	enum class State {
		LOADING,
		SUCCESS,
		ERROR_NOT_FOUND,
		ERROR_UNKNOWN,
		NO_INTERNET_CONNECTION
	}

	companion object {

		val LOADING = NetworkState(State.LOADING)
		val SUCCESS = NetworkState(State.SUCCESS)
		val ERROR_NOT_FOUND = NetworkState(State.ERROR_NOT_FOUND)
		val ERROR_UNKNOWN = NetworkState(State.ERROR_UNKNOWN)
		val NO_INTERNET_CONNECTION = NetworkState(State.NO_INTERNET_CONNECTION)
	}
}