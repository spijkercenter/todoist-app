package nl.spijkerman.ivo.todoist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.spijkerman.ivo.todoist.room.TodoistDatabase

class TodoViewModel : ViewModel() {

    private val _todoResult = MutableLiveData<String>()
    val todoResult: LiveData<String> get() = _todoResult

    private fun db(context: Context) = TodoistDatabase.getInstance(context)
    private fun dao(context: Context) = db(context).todoItemDao()

    fun getTodoItems(context: Context) {
        viewModelScope.launch {
            val todos: List<TodoItem> = dao(context).getTodos()
            val text = todos.joinToString("\n") { it.toString() }
            _todoResult.value = text
        }
    }

    fun postTodoItem(context: Context) {
        viewModelScope.launch {
            val todoItem = TodoItem(999, "UI bouwen", false)
//            TodoApi.retrofitService.postTodo(todoItem)
            dao(context).createTodo(todoItem)
            _todoResult.value = "Created $todoItem"
        }
    }

    fun deleteTodoItem(context: Context) {
        viewModelScope.launch {
            val id = 1
            dao(context).deleteTodo(id)
//            TodoApi.retrofitService.deleteTodo(id)
            _todoResult.value = "Delete todo $id"
        }
    }
}
