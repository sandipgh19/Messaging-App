package sandip.example.com.message.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import sandip.example.com.message.`object`.Message
import sandip.example.com.message.repo.AppRepository
import sandip.example.com.message.utils.remoteUtils.AbsentedLiveData
import javax.inject.Inject

class MessageListViewModel @Inject constructor(
    var repo: AppRepository
) : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    private val _refID: MutableLiveData<RepoCategory> = MutableLiveData()
    val refID: LiveData<RepoCategory>
        get() = _refID

    val result: LiveData<List<Message>> = Transformations
        .switchMap(_refID) { input ->
            input.ifExists { category ->
                repo.fetchData()
            }
        }

    fun init(refID: String) {
        val update = RepoCategory(refID)
        if (_refID.value == update) return
        _refID.postValue(update)
    }

    data class RepoCategory(val refID: String) {
        fun <T> ifExists(f: (String) -> LiveData<T>): LiveData<T> {
            return if (refID.isBlank()) {
                AbsentedLiveData.create()
            } else {
                f(refID)
            }
        }
    }
}