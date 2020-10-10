package com.sgjk.daggersample.di

import android.content.Context
import com.sgjk.daggersample.login.LoginActivity
import com.sgjk.daggersample.main.MainActivity
import com.sgjk.daggersample.register.RegisterActivity
import com.sgjk.daggersample.register.fragment.EnterDetailsFragment
import com.sgjk.daggersample.register.fragment.TermsAndConditionsFragment
import com.sgjk.daggersample.settings.SettingsActivity
import com.sgjk.daggersample.storage.StorageModule
import com.sgjk.daggersample.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 *@Provides annotation and Qualifie
 *
 * @Provides 告诉Dagger如何在Module提供一个实例。该函数的返回类型告诉Dagger 该类型被添加到了图中。该函数的参数
 * 是Dagger需要提供的一个依赖，在获取该返回值之前进行提供。
 *
 * 你可以再Module中使用Provides注解告诉Dagger如何提供：
 *  * 接口的实现 （尽管Binds被推荐，因此产生的代码较少，更为高效）
 *  * 第三方类
 *
 *  Qualifiers 是一个注解，用来标识一个依赖。
 *      class SharedPreferencesStorage @Inject constructor(name: String, context: Context) : Storage {
        private val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        }
    如果我们代码中有一个 将name作为参数的一个类
    我们可以通过Provides在StoreModule中提供不同的注解实现

        @Retention(AnnotationRetention.BINARY)
        @Qualifier
        annotation class RegistrationStorage

        @Retention(AnnotationRetention.BINARY)
        @Qualifier
        annotation class LoginStorage

        @Module
        class StorageModule {

        @RegistrationStorage
        @Provides
        fun provideRegistrationStorage(context: Context): Storage {
        return SharedPreferencesStorage("registration", context)
        }

        @LoginStorage
        @Provides
        fun provideLoginStorage(context: Context): Storage {
        return SharedPreferencesStorage("login", context)
        }
        }

        在上述例子中，我们定义了不同的Qualifier，表明我们将不同中的Storage放入图中。由于在Provides函数中，函数不起作用，所以使用Qualifier实现。

        // In a method
        class ClassDependingOnStorage(@RegistrationStorage private val storage: Storage) { ... }

        // As an injected field
        class ClassDependingOnStorage {

        @Inject
        @field:RegistrationStorage lateinit var storage: Storage
        }
 *
 */

//这是一个全局的声明周期 组件（容器）
@Singleton
@Component(modules = [StorageModule::class,AppSubComponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }

    //暴露 图中RegisterComponent.factory工厂方法
    //TODO 看看这个东西给谁用？ App吗
    fun registerComponent():RegisterComponent.factory

    fun loginComponent():LoginComponent.factory
    //错误使用
//    fun userComponent():UserComponent.Factory

    fun userManager(): UserManager

}