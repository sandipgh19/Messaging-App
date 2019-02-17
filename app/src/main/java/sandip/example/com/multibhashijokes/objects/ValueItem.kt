package sandip.example.com.multibhashijokes.objects

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "icndb_jokes",
    primaryKeys = ["id"]
)
data class ValueItem(

    @field:SerializedName("id")
    var id: Int = 0,

    @field:SerializedName("categories")
    var categories: List<String?>? = null,

    @field:SerializedName("joke")
    var joke: String? = null,

    var indexInResponse: Int? = -1
)
