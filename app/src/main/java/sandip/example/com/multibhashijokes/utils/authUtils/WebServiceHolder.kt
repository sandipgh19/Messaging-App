package sandip.example.com.multibhashijokes.utils.authUtils

import sandip.example.com.multibhashijokes.remote.WebServices

class WebServiceHolder {
    private var webservice: WebServices? = null

    fun apiService(): WebServices? {
        return this.webservice
    }

    fun setAPIService(webservice: WebServices) {
        this.webservice = webservice
    }

    companion object {
        internal var webServiceHolder: WebServiceHolder? = null

        val instance: WebServiceHolder
            get() {
                if (webServiceHolder == null) {
                    webServiceHolder = WebServiceHolder()
                }
                return webServiceHolder!!
            }
    }
}