package nl.spijkerman.ivo.todoist

import com.squareup.moshi.Json

data class TodoItem(

    @Json(name = "userId")
    val userId: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "completed")
    val completed: Boolean,

    @Json(name = "id")
    val id: Int? = null,
)
