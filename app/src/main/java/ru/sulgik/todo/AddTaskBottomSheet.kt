package ru.sulgik.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.sulgik.todo.data.Task
import ru.sulgik.todo.databinding.FragmentAddTaskBinding
import ru.sulgik.todo.utils.toStringOrEmpty

class AddTaskBottomSheet private constructor() : BottomSheetDialogFragment() {

    val binding by viewBinding<FragmentAddTaskBinding>()
    val tasksViewModel by sharedViewModel<TasksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val task = arguments?.getParcelable<Task>("task")

        binding.title.doOnTextChanged { text, start, before, count ->
            binding.titleLayout.isErrorEnabled = false
        }


        task?.let {
            binding.label.setText(R.string.edit_task_edit_label)
            binding.title.setText(it.title)
            binding.description.setText(it.description)
            binding.prioritySpinner.setSelection(task.priority, true)
        }
        binding.submit.setOnClickListener {
            val title = binding.title.text.toStringOrEmpty()
            if (title.isEmpty()) {
                binding.titleLayout.error = getString(R.string.edit_task_title_error)
                return@setOnClickListener;
            }

            val description = binding.description.text.toStringOrEmpty().trim()
            if (task == null) {
                tasksViewModel.insertTask(Task(
                    title = title,
                    description = description,
                    priority = binding.prioritySpinner.selectedItemPosition,
                ))
            } else {
                tasksViewModel.updateTask(Task(
                    title = title,
                    description = description,
                    priority = binding.prioritySpinner.selectedItemPosition,
                    id = task.id,
                ))
            }
            dismiss()
        }
    }

    companion object {
        const val taskKey = "task"

        fun create(task: Task? = null): AddTaskBottomSheet {
            return AddTaskBottomSheet().apply {
                arguments = (arguments ?: Bundle()).apply {
                    putParcelable(taskKey, task)
                }
            }
        }
    }
}