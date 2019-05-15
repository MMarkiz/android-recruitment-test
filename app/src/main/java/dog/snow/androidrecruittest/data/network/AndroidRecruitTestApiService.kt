package dog.snow.androidrecruittest.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.data.db.Item
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * author marcinm on 2019-05-14.
 */
interface AndroidRecruitTestApiService {
	companion object {
		operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): AndroidRecruitTestApiService {

			val okHttpClient = OkHttpClient.Builder()
				.addInterceptor(connectivityInterceptor)    //Comment this line for localhost
				.build()

			return Retrofit.Builder()
				.client(okHttpClient)
				.baseUrl("http://10.0.2.2:8080/api/")
				.addCallAdapterFactory(CoroutineCallAdapterFactory())
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(AndroidRecruitTestApiService::class.java)
		}
	}

	@GET("items")
	fun getItemsAsync(): Deferred<Response<ArrayList<Item>>>
}