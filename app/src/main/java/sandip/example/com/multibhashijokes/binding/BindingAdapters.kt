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

}