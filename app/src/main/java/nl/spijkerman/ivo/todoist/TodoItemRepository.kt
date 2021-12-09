package nl.spijkerman.ivo.todoist

import android.content.Context
import android.widget.Toast
import nl.spijkerman.ivo.todoist.room.TodoistDatabase


object TodoItemRepository {

    private fun api() = TodoApi.retrofitService
    private fun dao(context: Context) = TodoistDatabase.getInstance(context).todoItemDao()

    suspend fun getTodos(context: Context) = try {
        api().getTodos()
            .also { dao(context).clear() }
            .onEach { dao(context).createTodo(it) }
    } catch (e: Exception) {
        dao(context).getTodos()
    }

    suspend fun deleteTodo(context: Context, key: Int) {
        try {
            api().deleteTodo(key)
            dao(context).deleteTodo(key)
        } catch (e: Exception) {
            Toast.makeText(context, "No connection with server", Toast.LENGTH_LONG).show()
        }
    }

    suspend fun createTodo(context: Context, item: TodoItem): TodoItem {
        return try {
            val itemWithId = api().postTodo(item)
            dao(context).createTodo(itemWithId)
            itemWithId
        } catch (e: Exception) {
            Toast.makeText(context, "No connection with server", Toast.LENGTH_LONG).show()
            item
        }
    }
}