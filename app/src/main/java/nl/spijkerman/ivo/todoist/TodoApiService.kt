package nl.spijkerman.ivo.todoist

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:8080"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TodoApiService {

    @GET("/todos")
    // TODO verify that this works
    suspend fun getTodos(): List<TodoItem>

    @DELETE("/todos/{id}")
    suspend fun deleteTodo(@Path("id") id: Int)

    @POST("/todos")
    suspend fun postTodo(@Body item: TodoItem): TodoItem
}

object TodoApi {
    val retrofitService: TodoApiService by lazy {
        retrofit.create(TodoApiService::class.java)
    }
}