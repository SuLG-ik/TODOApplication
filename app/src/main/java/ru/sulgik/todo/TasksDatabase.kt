package ru.sulgik.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.sulgik.todo.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TasksDatabase: RoomDatabase() {

    abstract val tasksDao: TasksDAO

    companion object {
        fun buildInstance(context: Context): TasksDatabase {
            return Room.databaseBuilder(
                context,
                TasksDatabase::class.java,
                "todo"
            ).build()
        }
    }
}