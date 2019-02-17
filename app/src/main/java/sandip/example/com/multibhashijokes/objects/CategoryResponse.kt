package sandip.example.com.multibhashijokes.objects

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "icndb_jokes_category",
    primaryKeys = ["type"]
)
data class CategoryResponse(

    @field:SerializedName("type")
    var type: String = "",

    @field:SerializedName("value")
    var value: List<String?>? = null
)