package ru.sulgik.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sulgik.todo.data.Task
import ru.sulgik.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by viewBinding<ActivityMainBinding>()
    val tasksViewModel by viewModel<TasksViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tasksAdapter = TodoListAdapter()

        binding.tasks.layoutManager = LinearLayoutManager(this)
        binding.tasks.adapter = tasksAdapter

        lifecycleScope.launchWhenResumed {
            tasksViewModel.allTasks.collect {
                tasksAdapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            tasksAdapter.selectActions.collect {
                AddTaskBottomSheet.create(it).show(supportFragmentManager, "add_task")
            }
        }
        binding.addButton.setOnClickListener {
            AddTaskBottomSheet.create().show(supportFragmentManager, "add_task")
        }
    }
}