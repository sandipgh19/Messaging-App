package sandip.example.com.multibhashijokes.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sandip.example.com.multibhashijokes.MainActivity

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    abstract fun contributeStorageActivity(): MainActivity

}