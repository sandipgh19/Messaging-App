package sandip.example.com.multibhashijokes.binding

import android.databinding.BindingAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class BindingAdapters {

    @BindingAdapter(value = ["subString"], requireAll = true)
    fun setFirstCharacter(view: TextView, value: String?) {
        value?.let {
            view.text = value.substring(0,1)
        }

    }

    @BindingAdapter(value = ["timeStamp", "format", "emptyTxt"], requireAll = false)
    fun bindDateTime(textView: TextView, timeStamp: String?, format: String?, emptyTxt: String?) {

        try {
            if(timeStamp!=null) {
                var timeStamp1 =  timeStamp.toLong()
                val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                val mDate = Date(timeStamp1)
                textView.text = dateFormat.format(mDate)
            }else {
                textView.text = emptyTxt
            }

        }catch (e: Exception) {
            textView.text = emptyTxt
        }

    }

    @BindingAdapter(value = ["dayConversation"], requireAll = false)
    fun getHour(textView: TextView, time: String?) {

        try{

            val timeStamp = time?.toDouble()?:System.currentTimeMillis()
            val currentTime = System.currentTimeMillis()
            val oneHour = currentTime - 3600000
            val twoHour = currentTime - 2.times(3600000)
            val threeHour = currentTime - 3.times(3600000)
            val sixHour = currentTime - 6.times(3600000)
            val twelveHour = currentTime - 12.times(3600000)
            val oneDay = currentTime - 24.times(3600000)

            val day =  when {
                timeStamp in 0..oneHour -> "1 hours ago"
                timeStamp in twoHour..oneHour -> "2 hours ago"
                timeStamp in threeHour..sixHour -> "3 hours ago"
                timeStamp in sixHour..twelveHour -> "6 hours ago"
                timeStamp in twelveHour..oneDay -> "12 hours ago"
                else -> "1 day ago"
            }

            textView.text = day

        }catch (e: Exception){}

    }

}