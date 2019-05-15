package dog.snow.androidrecruittest

import android.app.Application
import android.content.Context
import dog.snow.androidrecruittest.data.db.AndroidRecruitTestDatabase
import dog.snow.androidrecruittest.data.network.*
import dog.snow.androidrecruittest.data.repository.AndroidRecruitTestRepository
import dog.snow.androidrecruittest.data.repository.AndroidRecruitTestRepositoryImpl
import dog.snow.androidrecruittest.ui.main.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * author marcinm on 2019-05-14.
 */
class AndroidRecruitTestApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@AndroidRecruitTestApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { AndroidRecruitTestApiService(instance()) }
        bind<AndroidRecruitTestDataSource>() with singleton { AndroidRecruitTestDataSourceImpl(instance()) }
        bind<AndroidRecruitTestRepository>() with singleton { AndroidRecruitTestRepositoryImpl(instance(), instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
        bind() from  singleton { AndroidRecruitTestDatabase(instance()) }
        bind() from  singleton { instance<AndroidRecruitTestDatabase>().itemDao() }
    }
}