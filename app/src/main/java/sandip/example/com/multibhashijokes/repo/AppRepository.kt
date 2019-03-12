package sandip.example.com.multibhashijokes.repo

import android.arch.lifecycle.MutableLiveData
import android.net.Uri
import sandip.example.com.multibhashijokes.`object`.Message
import sandip.example.com.multibhashijokes.provider.QueryHandler
import sandip.example.com.multibhashijokes.utils.helperUtils.AppExecutors
import javax.inject.Inject


class AppRepository @Inject constructor(
    private val executor: AppExecutors,
    private val mQueryHandler: QueryHandler) {

    fun fetchData(): MutableLiveData<List<Message>> {
        return query(
            0,
            Uri.parse("content://sms/inbox"), null, null,
            null, null
        )
    }

    private fun query(
        token: Int, uri: Uri,
        projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, orderby: String?
    ): MutableLiveData<List<Message>> {
        val result = MutableLiveData<List<Message>>()
        // Pass MutableLiveData in as a cookie, so we can set the result
        // in OnQueryComplete
        mQueryHandler.startQuery(
            token, result, uri, projection,
            selection, selectionArgs, orderby
        )
        return result
    }
}