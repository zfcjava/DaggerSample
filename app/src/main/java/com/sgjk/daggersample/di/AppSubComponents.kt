package com.sgjk.daggersample.di

import dagger.Module

//这个Module是用来告诉AppComponent，它有哪些SubComponent
@Module(subcomponents = [RegisterComponent::class,LoginComponent::class,UserComponent::class])
class AppSubComponents