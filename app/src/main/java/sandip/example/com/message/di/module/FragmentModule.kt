package sandip.example.com.message.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sandip.example.com.message.fragments.MessageListFragment

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeCategoryListFragment(): MessageListFragment
}