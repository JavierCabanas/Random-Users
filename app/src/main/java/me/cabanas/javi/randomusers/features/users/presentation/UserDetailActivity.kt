package me.cabanas.javi.randomusers.features.users.presentation


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import kotlinx.android.synthetic.main.toolbar.*
import me.cabanas.javi.randomusers.R
import me.cabanas.javi.randomusers.core.BUNDLE_CONST_USER_ID
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.framework.GlideApp
import me.cabanas.javi.randomusers.framework.base.BaseActivity
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserDetailViewModel

    override fun getLayoutResourceId(): Int = R.layout.activity_user_detail

    override fun getBaseLayout(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let {
            val uuid = it.getString(BUNDLE_CONST_USER_ID)
            uuid?.let { viewModel.loadUser(it) }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UserDetailViewModel::class.java)
        viewModel.user.observe(this, Observer {
            it?.let { renderUser(it) }
        })

        viewModel.failure.observe(this, Observer { failure ->
            failure?.let { (handleFailure(it)) }
        })
    }

    private fun renderUser(user: UserEntity) {
        with(user) {
            me.cabanas.javi.randomusers.framework.GlideApp.with(this@UserDetailActivity)
                    .load(picture.large)
                    .fitCenter()
                    .into(avatar)
            title = name.first + " " + name.last
            phoneContainer.field.text = phone
            phoneContainer.label.text = resources.getString(me.cabanas.javi.randomusers.R.string.phone_label)
            phoneContainer.icon.setImageResource(me.cabanas.javi.randomusers.R.drawable.ic_phone)
            phoneContainer.secondaryIcon.setImageResource(me.cabanas.javi.randomusers.R.drawable.ic_chat)
            phoneContainer.secondaryIcon.visibility = android.view.View.VISIBLE

            when {
                cell.isEmpty() -> cellContainer.visibility = android.view.View.GONE
                else -> {
                    cellContainer.field.text = cell
                    cellContainer.label.text = resources.getString(me.cabanas.javi.randomusers.R.string.cell_label)
                    cellContainer.icon.visibility = android.view.View.GONE
                    cellContainer.secondaryIcon.setImageResource(me.cabanas.javi.randomusers.R.drawable.ic_chat)
                    cellContainer.secondaryIcon.visibility = android.view.View.VISIBLE
                    phoneContainer.separator.visibility = android.view.View.INVISIBLE
                }
            }

            emailContainer.field.text = email
            emailContainer.label.text = resources.getString(me.cabanas.javi.randomusers.R.string.email_label)
            emailContainer.icon.setImageResource(me.cabanas.javi.randomusers.R.drawable.ic_mail)

            addressContainer.field.text = resources.getString(me.cabanas.javi.randomusers.R.string.address,
                    location.street, location.city, location.state, location.postcode)
            addressContainer.label.text = resources.getString(me.cabanas.javi.randomusers.R.string.address_label)
            addressContainer.icon.setImageResource(me.cabanas.javi.randomusers.R.drawable.ic_location)

        }
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
        collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
        collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.RemoteRepoFailure -> renderFailure(R.string.failure_remote)
            is Failure.LocalRepoFailure -> renderFailure(R.string.failure_local)
            is Failure.InteractorFailure -> renderFailure(R.string.failure_default)
        }
    }

    private fun renderFailure(messageId: Int) {
        val snackbar = Snackbar.make(toolbar, messageId, Snackbar.LENGTH_SHORT)
        snackbar.show()
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                this@UserDetailActivity.finish()
            }
        })
    }

}
