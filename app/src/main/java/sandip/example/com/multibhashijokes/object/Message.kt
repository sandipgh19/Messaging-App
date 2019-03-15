package sandip.example.com.multibhashijokes.`object`

import android.R.id
import android.content.ContentValues



data class Message(

    var coloumID: String = "",
    var threadID: String? = "",
    var address: String? = "",
    var person: String? = "",
    var date: String? = "",
    var dateSent: String? = "",
    var protocol: String? = "",
    var read: String? = "",
    var status: String? = "",
    var type: String? = "",
    var reply_path_present: String? = "",
    var subject: String? = "",
    var body: String? = "",
    var seen: String? = "",
    var outTime: String? = "",
    var header: String = "",
    var isVisible: Boolean = false

)