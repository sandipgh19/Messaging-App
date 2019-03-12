package sandip.example.com.multibhashijokes.provider

import android.arch.lifecycle.MutableLiveData
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.database.Cursor
import sandip.example.com.multibhashijokes.`object`.Message

class QueryHandler(val resolver: ContentResolver) : AsyncQueryHandler(resolver) {

    override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
        super.onQueryComplete(token, cookie, cursor)

        val mutableData: MutableLiveData<List<Message>> = MutableLiveData()
        try {
            when (token) {
                0 -> {

                    if (cursor != null && cursor.moveToFirst()) {

                        val messageList: MutableList<Message> = ArrayList()

                        do {
                            val message = Message()
                            message.coloumID = cursor.getColumnName(0)

                            messageList.add(message)

                        }while (cursor.moveToNext())

                        mutableData.value = messageList

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