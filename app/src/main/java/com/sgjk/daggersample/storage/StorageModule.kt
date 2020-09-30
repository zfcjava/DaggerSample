package com.sgjk.daggersample.storage

import dagger.Binds
import dagger.Module

//告诉Dagger这是一个Module类
@Module
abstract class StorageModule {

    //告诉Dagger如果需要一个Storage 对象，Dagger需要系统一个SharedPreferencesStorage对象
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage):Storage
}