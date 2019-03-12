package sandip.example.com.multibhashijokes.database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import sandip.example.com.multibhashijokes.data.AppDao

@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}