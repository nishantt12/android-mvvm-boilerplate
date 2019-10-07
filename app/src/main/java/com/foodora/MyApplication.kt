package com.foodora

import android.app.Application
import com.foodora.di.component.AppComponent
import com.foodora.di.component.DaggerAppComponent
import com.foodora.di.module.ContextModule
import io.realm.Realm
import timber.log.Timber

class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent

        fun getComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Realm.init(this)
        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }
}