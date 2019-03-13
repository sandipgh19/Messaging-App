package sandip.example.com.multibhashijokes.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sandip.example.com.multibhashijokes.di.ViewModelKey
import sandip.example.com.multibhashijokes.factory.AppModelFactory
import sandip.example.com.multibhashijokes.fragments.MessageListFragment
import sandip.example.com.multibhashijokes.viewModel.MessageListViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MessageListViewModel::class)
    abstract fun bindJokesListViewModel(viewModel: MessageListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppModelFactory): ViewModelProvider.Factory
}