package me.cabanas.javi.randomusers.features.users.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_users.*
import me.cabanas.javi.randomusers.R
import me.cabanas.javi.randomusers.core.Router
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.framework.base.BaseActivity
import javax.inject.Inject

class UsersActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var usersAdapter: UsersAdapter
    @Inject
    lateinit var router: Router

    private lateinit var viewModel: UsersViewModel

    override fun getLayoutResourceId(): Int = R.layout.activity_users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadUsers()
    }

    override fun initView() {
        val layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        userList.layoutManager = layoutManager
        userList.adapter = usersAdapter
        usersAdapter.itemClick = {
            router.openUserDetail(this, it.email)
        }
        userList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.itemCount == layoutManager.findLastVisibleItemPosition() + 1)
                    viewModel.loadUsers()
            }
        })
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UsersViewModel::class.java)
        viewModel.users.observe(this, Observer {
            usersAdapter.users = it.orEmpty()
        })

        viewModel.failure.observe(this, Observer {
            handlFailure(it)
        })
    }

    private fun handlFailure(failure: Failure?) {
        when (failure) {
            is Failure.RemoteRepoFailure -> renderFailure(R.string.failure_remote)
            is Failure.LocalRepoFailure -> renderFailure(R.string.failure_local)
            is Failure.InteractorFailure -> renderFailure(R.string.failure_default)
        }
    }

    private fun renderFailure(messageId: Int) {
        val snackbar = Snackbar.make(userList, messageId, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }
}