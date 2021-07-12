package ru.sulgik.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.sulgik.todo.data.Task


@Dao
interface TasksDAO {

    @Query("SELECT * FROM task ORDER BY priority DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteById(id: Long)

}