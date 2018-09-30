package me.cabanas.javi.randomusers.core

import android.content.Context

interface Router {
    fun openUserDetail(context: Context, email: String)
}
