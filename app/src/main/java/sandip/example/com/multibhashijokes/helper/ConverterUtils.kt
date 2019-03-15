package sandip.example.com.multibhashijokes.helper

import android.util.Log
import android.widget.TextView

class ConverterUtils {

    fun getHour(time: String?): String {
        val timeStamp = time?.toDouble() ?: System.currentTimeMillis()
        val currentTime = System.currentTimeMillis()
        val oneHour = currentTime - 3600000
        val twoHour = currentTime - 7200000
        val threeHour = currentTime - 10800000
        val sixHour = currentTime - 21600000
        val twelveHour = currentTime - 43200000
        val oneDay = currentTime - 86400000

        return when(timeStamp) {
            0..oneHour -> "1 hours ago"
            twoHour..oneHour -> "2 hours ago"
            threeHour..sixHour -> "3 hours ago"
            sixHour..twelveHour -> "6 hours ago"
            twelveHour..oneDay -> "12 hours ago"
            else -> "1 day ago"
        }
    }

    fun checkValues(privious: String, current: String) = privious!=current
}