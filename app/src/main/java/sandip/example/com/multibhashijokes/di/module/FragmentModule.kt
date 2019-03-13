package sandip.example.com.multibhashijokes.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sandip.example.com.multibhashijokes.fragments.MessageListFragment

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeCategoryListFragment(): MessageListFragment
}