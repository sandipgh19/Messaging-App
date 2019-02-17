package sandip.example.com.multibhashijokes.objects

import com.google.gson.annotations.SerializedName

data class JokesResponse(

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("value")
    val value: List<ValueItem?>? = null
)
