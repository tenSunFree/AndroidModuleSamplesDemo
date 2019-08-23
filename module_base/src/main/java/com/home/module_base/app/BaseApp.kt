package com.home.module_base.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.home.module_base.BuildConfig
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

open class BaseApp : Application() {

    private var refWatcher: RefWatcher? = null

    companion object {

        var instance: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val app = context.applicationContext as BaseApp
            return app.refWatcher
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        refWatcher = setupLeakCanary()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        initRouter()
    }

    private fun initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this) // 解決64K限制
    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    private val mActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d("more", "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d("more", "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {
            Log.d("more", "onActivityResumed: " + activity.componentName.className)
        }

        override fun onActivityPaused(activity: Activity) {
            Log.d("more", "onActivityPaused: " + activity.componentName.className)
        }

        override fun onActivityStopped(activity: Activity) {
            Log.d("more", "onActivityStopped: " + activity.componentName.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            Log.d("more", "onActivitySaveInstanceState: " + activity.componentName.className)
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d("more", "onDestroy: " + activity.componentName.className)
        }
    }
}