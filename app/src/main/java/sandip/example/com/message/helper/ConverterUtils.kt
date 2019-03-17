package sandip.example.com.message.helper

class ConverterUtils {

    fun getHour(time: Long?): String {
        val timeStamp = time ?: System.currentTimeMillis()
        val currentTime = System.currentTimeMillis()
        val oneHour = currentTime - 3600000
        val twoHour = currentTime - 7200000
        val threeHour = currentTime - 10800000
        val sixHour = currentTime - 21600000
        val twelveHour = currentTime - 43200000

        return when(timeStamp) {
            in oneHour..currentTime -> "1 hours ago"
            in twoHour..(oneHour-1) -> "2 hours ago"
            in threeHour..(twoHour-1) -> "3 hours ago"
            in sixHour..(threeHour-1) -> "6 hours ago"
            in twelveHour..(sixHour-1) -> "12 hours ago"
            else -> "1 day ago"
        }
    }

    fun checkValues(previous: String?, current: String?) = previous!=current
}