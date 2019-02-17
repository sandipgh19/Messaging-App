package sandip.example.com.multibhashijokes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import sandip.example.com.multibhashijokes.data.AppDao
import sandip.example.com.multibhashijokes.objects.CategoryResponse
import sandip.example.com.multibhashijokes.objects.ValueItem

@Database(
    entities = [
        (CategoryResponse::class),
        (ValueItem::class)],
    version = 1, exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}