package nl.spijkerman.ivo.todoist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val _todoResult = MutableLiveData<String>()
    val todoResult: LiveData<String> get() = _todoResult

    fun getTodoItems() {
        viewModelScope.launch {
            val todos: String = TodoApi.retrofitService.getTodos()
            _todoResult.value = todos
        }
    }

    fun postTodoItem() {
        viewModelScope.launch {
            val todoItem = TodoItem(999, "UI bouwen", false)
            TodoApi.retrofitService.postTodo(todoItem)
            _todoResult.value = "Created $todoItem"
        }
    }

    fun deleteTodoItem() {
        viewModelScope.launch {
            val id = 1
            TodoApi.retrofitService.deleteTodo(id)
            _todoResult.value = "Delete todo $id"
        }
    }
}
