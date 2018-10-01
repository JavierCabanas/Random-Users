package me.cabanas.javi.randomusers.framework

import android.content.Context
import android.content.Intent
import me.cabanas.javi.randomusers.core.BUNDLE_CONST_USER_ID
import me.cabanas.javi.randomusers.core.Router
import me.cabanas.javi.randomusers.features.users.presentation.UserDetailActivity

class AndroidRouter : Router {
    override fun openUserDetail(context: Context, uuid: String) {
        val intent = Intent(context, UserDetailActivity::class.java)
        intent.putExtra(BUNDLE_CONST_USER_ID, uuid)
        context.startActivity(intent)
    }

}