package nl.spijkerman.ivo.todoist

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class TodoItem(

    @Json(name = "userId")
    val userId: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "completed")
    val completed: Boolean,

    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int? = null,
)
