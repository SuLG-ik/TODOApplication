package ru.sulgik.todo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "task")
@Parcelize
class Task(
    val title: String,
    val description: String,
    val priority: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
) : Parcelable {

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + priority
        result = 31 * result + id.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (title != other.title) return false
        if (priority != other.priority) return false
        if (id != other.id) return false

        return true
    }

}