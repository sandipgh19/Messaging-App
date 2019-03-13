package sandip.example.com.multibhashijokes.provider

import android.arch.lifecycle.MutableLiveData
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.database.Cursor
import android.widget.Toast
import sandip.example.com.multibhashijokes.AppController
import sandip.example.com.multibhashijokes.`object`.Message

class QueryHandler(val resolver: ContentResolver) : AsyncQueryHandler(resolver) {

    override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
        super.onQueryComplete(token, cookie, cursor)

        //val mutableData: MutableLiveData<List<Message>> = cookie as MutableLiveData()

        try {
            when (token) {
                0 -> {

                    Toast.makeText(AppController.instance, "Cursor Size: ${cursor?.columnCount} -- Move: ${cursor?.moveToFirst()}", Toast.LENGTH_LONG).show()
                    if (cursor != null && cursor.moveToFirst()) {

                        val messageList: MutableList<Message> = ArrayList()

                        do {
                            val message = Message()
                            message.coloumID = cursor.getString(cursor.getColumnIndex("_id"))

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