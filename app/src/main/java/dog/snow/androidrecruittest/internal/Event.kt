package dog.snow.androidrecruittest.internal


/**
 * author marcinm on 2019-05-14.
 */
open class Event<out T>(private val content: T) {

	var hasBeenHandled = false
		private set

	fun getContent(): T? {
		return content
	}

	fun getContentIfNotHandled(): T? {
		return if (hasBeenHandled) {
			null
		} else {
			hasBeenHandled = true
			content
		}
	}
}