package dog.snow.androidrecruittest.internal

import kotlinx.coroutines.*

/**
 * author marcinm on 2019-05-14.
 */
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}