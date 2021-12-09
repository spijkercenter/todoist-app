package nl.spijkerman.ivo.todoist.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import nl.spijkerman.ivo.todoist.TodoItem

@Dao
interface TodoItemDao {

    @Query("select * from TodoItem")
    suspend fun getTodos(): List<TodoItem>

    @Query("delete from TodoItem where id = :key")
    suspend fun deleteTodo(key: Int)

    @Insert
    suspend fun createTodo(item: TodoItem)
}