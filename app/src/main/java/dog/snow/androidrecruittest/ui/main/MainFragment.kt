package dog.snow.androidrecruittest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.data.model.NetworkState
import dog.snow.androidrecruittest.ui.base.AndroidRecruitTestFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


/**
 * author marcinm on 2019-05-14.
 */
class MainFragment : AndroidRecruitTestFragment(), KodeinAware {

    override val kodein by kodein()
    private val adapter = ItemsRecyclerViewAdapter()
    private val viewModelFactory: MainViewModelFactory by instance()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerview_main_items.layoutManager = LinearLayoutManager(activity)
        recyclerview_main_items.adapter = adapter

        refreshlayout_main_items.setOnRefreshListener { updateItemsAsync() }

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
        items.observe(this@MainFragment, Observer {
            refreshlayout_main_items.isRefreshing = false
            adapter.setData(it)
            if (it.isEmpty()) {
                item_main_empty.visibility = View.VISIBLE
            } else {
                item_main_empty.visibility = View.GONE
            }
        })
    }

    private fun updateItemsAsync() = launch {
        mainViewModel.fetchItems()
    }

    private fun observeLoadingState(state: NetworkState) {
        if (state.state == NetworkState.State.LOADING) {
            showLoadingDialog(true)
        } else {
            showLoadingDialog(false)
        }
    }

    private fun observeNetworkState(state: NetworkState) = launch {
        when (state.state) {

            NetworkState.State.LOADING -> {
            }

            NetworkState.State.SUCCESS -> showSnackMessage(R.string.main_refreshed_info)

            NetworkState.State.ERROR_NOT_FOUND -> {
                showSnackMessage(R.string.main_error_not_found)
                refreshlayout_main_items.isRefreshing = false
            }

            NetworkState.State.ERROR_UNKNOWN -> {
                showSnackMessage(R.string.main_error_unknown)
                refreshlayout_main_items.isRefreshing = false
            }

            NetworkState.State.NO_INTERNET_CONNECTION -> {
                showSnackMessage(R.string.main_error_network_connection)
                refreshlayout_main_items.isRefreshing = false
            }

        }
    }
}