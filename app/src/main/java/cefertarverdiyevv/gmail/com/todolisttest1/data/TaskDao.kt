package cefertarverdiyevv.gmail.com.todolisttest1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Update
    fun updateTask(user:Task)

    @Insert
    fun insertAll(vararg users:Task)
    @Delete
    fun delete(user: Task)

}