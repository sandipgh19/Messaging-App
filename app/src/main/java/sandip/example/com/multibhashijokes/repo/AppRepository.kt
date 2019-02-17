package sandip.example.com.multibhashijokes.repo

import sandip.example.com.multibhashijokes.data.AppDao
import sandip.example.com.multibhashijokes.remote.WebServices
import sandip.example.com.multibhashijokes.utils.helperUtils.AppExecutors
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val webservice: WebServices,
    private val executor: AppExecutors,
    private val dao: AppDao) {
}