package nl.spijkerman.ivo.todoist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import nl.spijkerman.ivo.todoist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val viewModel: TodoViewModel by viewModels()

        viewModel.todoResult.observe(this) {
            binding.resultTv.text = viewModel.todoResult.value
        }

        binding.deleteBtn.setOnClickListener { viewModel.deleteTodoItem() }
        binding.getBtn.setOnClickListener { viewModel.getTodoItems() }
        binding.postBtn.setOnClickListener { viewModel.postTodoItem() }
    }
}