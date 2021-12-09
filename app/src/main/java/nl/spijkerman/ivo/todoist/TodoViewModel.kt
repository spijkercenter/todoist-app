package nl.spijkerman.ivo.todoist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val _todoResult = MutableLiveData<String>()
    val todoResult: LiveData<String> get() = _todoResult

    fun getTodoItems(context: Context) {
        viewModelScope.launch {
            val todos: List<TodoItem> = TodoItemRepository.getTodos(context)
            val text = todos.joinToString("\n") { it.toString() }
            _todoResult.value = text
        }
    }

    fun postTodoItem(context: Context) {
        viewModelScope.launch {
            var todoItem = TodoItem(999, "UI bouwen", false)
//            TodoApi.retrofitService.postTodo(todoItem)
            todoItem = TodoItemRepository.createTodo(context, todoItem)
            _todoResult.value = "Created $todoItem"
        }
    }

    fun deleteTodoItem(context: Context) {
        viewModelScope.launch {
            val id = 1
            TodoItemRepository.deleteTodo(context, id)
//            TodoApi.retrofitService.deleteTodo(id)
            _todoResult.value = "Delete todo $id"
        }
    }
}
