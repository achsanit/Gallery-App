package com.achsanit.utils.di

import com.achsanit.utils.CustomInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideCustomInterceptor(): CustomInterceptor {
        return CustomInterceptor()
    }

}