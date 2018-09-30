package me.cabanas.javi.randomusers.framework.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_base.*
import me.cabanas.javi.randomusers.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateLayout()
        initView()
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

    abstract fun getLayoutResourceId(): Int

    protected fun getBaseLayout() = R.layout.activity_base

    open protected fun initView() {

    }
}
