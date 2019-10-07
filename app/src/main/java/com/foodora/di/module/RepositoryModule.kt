package com.foodora.di.module

import com.foodora.repository.api.CatalogService
import com.foodora.repository.CatalogRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
class RepositoryModule {

  @Singleton
  @Provides
  fun provideUserRepository(userService: CatalogService): CatalogRepository {
    return CatalogRepository(userService)
  }
}