package sandip.example.com.message.provider

import android.arch.lifecycle.MutableLiveData
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.database.Cursor
import android.support.annotation.MainThread
import sandip.example.com.message.`object`.Message

class QueryHandler(private val resolver: ContentResolver) : AsyncQueryHandler(resolver) {

    @MainThread
    override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
        super.onQueryComplete(token, cookie, cursor)

        try {
            when (token) {
                0 -> {
                    if (cursor != null && cursor.moveToFirst()) {
                        val messageList: MutableList<Message> = ArrayList()
                        do {
                            val message = Message()
                            message.coloumID = cursor.getString(cursor.getColumnIndex("_id"))
                            message.body = cursor.getString(cursor.getColumnIndex("body"))
                            message.person = cursor.getString(cursor.getColumnIndex("person"))
                            message.address = cursor.getString(cursor.getColumnIndex("address"))
                            message.date = cursor.getLong(cursor.getColumnIndex("date"))
                            message.type = cursor.getString(cursor.getColumnIndex("type"))
                            message.threadID = cursor.getString(cursor.getColumnIndex("thread_id"))
                            messageList.add(message)
                        }while (cursor.moveToNext())
                        (cookie as MutableLiveData<List<Message>>).postValue(messageList)
                    }
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}