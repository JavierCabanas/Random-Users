package me.cabanas.javi.randomusers.feature.users.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import dagger.android.AndroidInjection
import me.cabanas.javi.randomusers.R
import me.cabanas.javi.randomusers.framework.base.BaseActivity
import javax.inject.Inject

class UsersActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UsersViewModel

    override fun getLayoutResourceId(): Int = R.layout.activity_users

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
        viewModel.loadUsers()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UsersViewModel::class.java)
        viewModel.users.observe(this, Observer {
            Toast.makeText(this, "list updated", Toast.LENGTH_LONG).show()
        })
    }
}