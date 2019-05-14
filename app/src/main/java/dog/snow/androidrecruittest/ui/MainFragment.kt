package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.base.AndroidRecruitTestFragment
import dog.snow.androidrecruittest.data.model.NetworkState
import dog.snow.androidrecruittest.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * author marcinm on 2019-05-14.
 */
class MainFragment : AndroidRecruitTestFragment(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: MainViewModelFactory by instance()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        binding.apply { this.viewmodel = mainViewModel }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind()
        mainViewModel.networkState.observe(viewLifecycleOwner, Observer {
            it.getContent()?.let { state ->
                observeLoadingState(state)
            }
            it.getContentIfNotHandled()?.let { state ->
                observeNetworkState(state)
            }
        })

    }

    private fun bind() = launch {
        val items = mainViewModel.items.await()
//          mainViewModel.fetchItems()

        items.observe(this@MainFragment, Observer {
            Log.d("itemss", it.size.toString())
            if(it.isNotEmpty()){
                Log.d("itemss", it[0].uri)
            }
        })
    }

    private fun observeLoadingState(state: NetworkState) {
        if (state.state == NetworkState.State.LOADING) {
            Log.d("itemss", "ok")
            showLoadingDialog(true)
        } else {
            Log.d("itemss", "end")
            showLoadingDialog(false)
        }
    }

    private fun observeNetworkState(state: NetworkState) {
        when (state.state) {

            NetworkState.State.LOADING -> { }

            NetworkState.State.SUCCESS -> Log.d("itemss", "success")

            NetworkState.State.ERROR_NOT_FOUND -> Log.d("itemss", "not found")

            NetworkState.State.ERROR_UNKNOWN -> Log.d("itemss", "unknown")

            NetworkState.State.NO_INTERNET_CONNECTION -> Log.d("itemss", "no internet")

        }
    }
}