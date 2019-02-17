package sandip.example.com.multibhashijokes.utils.remoteUtils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(var status: Status, val data: T?, val message: String?, val code: Int) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, -1)
        }

        fun <T> error(msg: String, data: T?, code: Int): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, -1)
        }
    }
}