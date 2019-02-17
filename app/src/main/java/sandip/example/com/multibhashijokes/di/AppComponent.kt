package sandip.example.com.multibhashijokes.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import sandip.example.com.multibhashijokes.AppController
import sandip.example.com.multibhashijokes.di.module.ActivityModule
import sandip.example.com.multibhashijokes.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: AppController)
}