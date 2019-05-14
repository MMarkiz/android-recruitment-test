package dog.snow.androidrecruittest.data.repository

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import dog.snow.androidrecruittest.base.AndroidRecruitTestApplication
import dog.snow.androidrecruittest.data.db.Item
import dog.snow.androidrecruittest.data.db.ItemDao
import dog.snow.androidrecruittest.data.model.NetworkState
import dog.snow.androidrecruittest.data.network.AndroidRecruitTestDataSource
import dog.snow.androidrecruittest.internal.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.android.kodein
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.ref.WeakReference

/**
 * author marcinm on 2019-05-14.
 */
class AndroidRecruitTestRepositoryImpl(
    private val androidNetworkDataSource: AndroidRecruitTestDataSource,
    private val itemDao: ItemDao,
    private val context: Context
) : AndroidRecruitTestRepository {


    init {
        androidNetworkDataSource.apply {
            downloadItems.observeForever { persistFetchedCurrentWeather(it) }
        }
    }

    override fun getNetworkState(): LiveData<Event<NetworkState>> {
        return androidNetworkDataSource.networkState
    }

    override suspend fun getItems(): LiveData<List<Item>> {
        return withContext(Dispatchers.IO) {
            return@withContext itemDao.getItem()
        }
    }

    override suspend fun fetchItems() {
        androidNetworkDataSource.fetchItems()
    }


    private fun persistFetchedCurrentWeather(items: List<Item>) {
        GlobalScope.launch(Dispatchers.IO) {
            val itemsWithUri = saveImagesToInternalStorage(items)
            itemDao.insertItemsList(itemsWithUri)
        }
    }

    private fun saveImagesToInternalStorage(items: List<Item>): List<Item> {
            val mContext: WeakReference<Context> = WeakReference(context)

            val requestOptions = RequestOptions().override(100)
                .downsample(DownsampleStrategy.CENTER_INSIDE)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)

            for (item in items) {
                mContext.get()?.let {
                    val bitmap = Glide.with(it)
                        .asBitmap()
                        .load(item.icon)
                        .apply(requestOptions)
                        .submit()
                        .get()

                    val wrapper = ContextWrapper(context)
                    var file = wrapper.getDir("images", Context.MODE_PRIVATE)
                    file = File(file, "${item.name}.jpg")

                    try {
                        val stream: OutputStream = FileOutputStream(file)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                        stream.flush()
                        stream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    item.uri = file.absolutePath
                }
            }
        return items
    }
}