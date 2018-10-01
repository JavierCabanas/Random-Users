package me.cabanas.javi.randomusers.framework.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_base.*
import me.cabanas.javi.randomusers.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        inflateLayout()
        initView()
        initViewModel()
    }

    private fun inflateLayout() {
        if (getBaseLayout() == 0) {
            setContentView(getLayoutResourceId())
        } else {
            setContentView(getBaseLayout())
            val view = LayoutInflater.from(this)
                    .inflate(getLayoutResourceId(), content, false)
            content.addView(view)
        }
    }

    protected abstract fun getLayoutResourceId(): Int

    protected fun getBaseLayout() = R.layout.activity_base

    open protected fun initView() {
    }

    protected abstract fun initViewModel()
}
