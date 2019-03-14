package sandip.example.com.multibhashijokes.utils.permissionUtil

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver

interface ComponentLifecycleObserver : LifecycleObserver {
    fun <T> initWith(owner: T)
}

fun <T> Lifecycle.addObserver(observer: ComponentLifecycleObserver, owner: T) {
    addObserver(observer as LifecycleObserver)
    observer.initWith(owner)
}

interface PermissionDelegate {

    fun requestAndRun(permissions: List<String>, failAction: (List<String>) -> Unit, action: () -> Unit)

    fun requestThenRun(permissions: List<String>, failAction: (List<String>) -> Unit, action: () -> Unit)
}