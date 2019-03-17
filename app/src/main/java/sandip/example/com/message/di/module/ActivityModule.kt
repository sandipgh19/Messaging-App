package sandip.example.com.message.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sandip.example.com.message.MainActivity

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    abstract fun contributeStorageActivity(): MainActivity

}