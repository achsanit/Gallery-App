package com.achsanit.core.data.di

import com.achsanit.core.data.Repository
import com.achsanit.core.network.service.GalleryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal object DataModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(service: GalleryService): Repository {
        return Repository(service)
    }

}