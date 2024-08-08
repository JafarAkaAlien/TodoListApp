package cefertarverdiyevv.gmail.com.todolisttest1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey (autoGenerate = true) val id: Int,
    @ColumnInfo("name") var name:String
)
