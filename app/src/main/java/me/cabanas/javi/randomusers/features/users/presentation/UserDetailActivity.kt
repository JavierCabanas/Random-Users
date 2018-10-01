package me.cabanas.javi.randomusers.features.users.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_users.*
import me.cabanas.javi.randomusers.R
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.framework.base.BaseActivity

class UserDetailActivity : BaseActivity() {
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserDetailViewModel

    override fun getLayoutResourceId(): Int = 0

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UserDetailViewModel::class.java)
        viewModel.user.observe(this, Observer {
            it?.let {
                with(it) {
                    TODO("bind to UI")
                }
            }
        })

        viewModel.failure.observe(this, Observer { failure ->
            failure?.let { (handleFailure(it)) }
        })
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.RemoteRepoFailure -> renderFailure(R.string.failure_remote)
            is Failure.LocalRepoFailure -> renderFailure(R.string.failure_local)
            is Failure.InteractorFailure -> renderFailure(R.string.failure_default)
        }
    }

    private fun renderFailure(messageId: Int) {
        val snackbar = Snackbar.make(userList, messageId, Snackbar.LENGTH_SHORT)
        snackbar.show()
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                this@UserDetailActivity.finish()
            }
        })
    }

}
