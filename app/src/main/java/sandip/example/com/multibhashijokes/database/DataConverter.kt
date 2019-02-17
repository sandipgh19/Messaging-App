package sandip.example.com.multibhashijokes.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

class DataConverter {

    @TypeConverter
    fun listToJson(value: MutableList<String?>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String?): MutableList<String?>? {

        val objects = Gson().fromJson(value, Array<String?>::class.java)
        val list = objects?.toMutableList()
        return list
    }
}