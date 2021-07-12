package ru.sulgik.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.sulgik.todo.data.Task
import ru.sulgik.todo.databinding.TaskItemBinding

class TodoListAdapter : ListAdapter<Task, TodoListAdapter.Item>(Diff) {

    private val _selectActions =
        MutableSharedFlow<Task>(extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_LATEST)
    val selectActions = _selectActions.asSharedFlow()

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding by viewBinding<TaskItemBinding>()

        fun bind(task: Task, clickListener: (Task) -> Unit) {
            binding.task = task
            if (task.description.isNotEmpty())
                binding.root.setOnClickListener {
                    val visibility = binding.description.visibility
                    binding.description.visibility =
                        if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
                }
            binding.root.setOnLongClickListener {
                clickListener(task)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.bind(currentList[position]) {
            _selectActions.tryEmit(it)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }


    }
}