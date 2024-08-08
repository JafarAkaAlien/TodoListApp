package cefertarverdiyevv.gmail.com.todolisttest1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database ([Task::class], version = 1)
abstract class MainDatabase:RoomDatabase() {
    abstract fun taskDao():TaskDao

    companion object{

        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context):MainDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }


}