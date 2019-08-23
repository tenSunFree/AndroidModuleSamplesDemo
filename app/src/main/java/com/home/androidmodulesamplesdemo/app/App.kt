package com.home.androidmodulesamplesdemo.app

import android.content.Context
import androidx.multidex.MultiDex
import com.home.module_base.app.BaseApp

class App : BaseApp() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}