package sandip.example.com.multibhashijokes.`object`

import android.R.id
import android.content.ContentValues



data class Message(

    var coloumID: String = "",
    var threadID: String = "",
    var address: String = "",
    var person: String = "",
    var date: String = "",
    var dateSent: String = "",
    var protocol: String = "",
    var read: String = "",
    var status: String = "",
    var type: String = "",
    var reply_path_present: String = "",
    var subject: String = "",
    var body: String = "",
    var seen: String = "",
    var outTime: String = ""

) {

    fun fromContentValues(values: ContentValues?): Message {
        val message = Message()
        if(values!=null) {

            if (values.containsKey(coloumID)) {
                message.coloumID = values.getAsString(coloumID)
            }
            if (values.containsKey(threadID)) {
                message.threadID = values.getAsString(threadID)
            }
            if (values.containsKey(address)) {
                message.address = values.getAsString(address)
            }
            if (values.containsKey(person)) {
                message.person = values.getAsString(person)
            }
            if (values.containsKey(date)) {
                message.date = values.getAsString(date)
            }
            if (values.containsKey(dateSent)) {
                message.dateSent = values.getAsString(dateSent)
            }
            if (values.containsKey(protocol)) {
                message.protocol = values.getAsString(protocol)
            }
            if (values.containsKey(read)) {
                message.read = values.getAsString(read)
            }
        }

        return message
    }
}