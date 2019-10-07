package com.foodora.di.component

import com.foodora.activities.*
import com.foodora.di.module.ContextModule
import com.foodora.di.module.NetworkModule
import com.foodora.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, ContextModule::class])
interface AppComponent {

    fun injectCatalogActivity(catalogActivity: CatalogActivity)

    fun injectProductActivity(productActivity: ProductActivity)


}