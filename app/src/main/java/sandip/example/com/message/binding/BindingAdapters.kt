package sandip.example.com.message.binding

import android.databinding.BindingAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class BindingAdapters {

    @BindingAdapter(value = ["timeStamp", "format", "emptyTxt"], requireAll = false)
    fun bindDateTime(textView: TextView, timeStamp: Long?, format: String?, emptyTxt: String?) {

        try {
            if(timeStamp!=null) {
                val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                val mDate = Date(timeStamp)
                textView.text = dateFormat.format(mDate)
            }else {
                textView.text = emptyTxt
            }

        }catch (e: Exception) {
            textView.text = emptyTxt
        }

    }

}