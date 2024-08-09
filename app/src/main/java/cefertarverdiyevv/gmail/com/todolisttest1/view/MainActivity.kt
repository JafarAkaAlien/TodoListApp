package cefertarverdiyevv.gmail.com.todolisttest1.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cefertarverdiyevv.gmail.com.todolisttest1.R
import cefertarverdiyevv.gmail.com.todolisttest1.data.MainDatabase
import cefertarverdiyevv.gmail.com.todolisttest1.data.Task
import cefertarverdiyevv.gmail.com.todolisttest1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db:MainDatabase
    private lateinit var taskAdapter:TaskAdapter
    private lateinit var taskList:MutableList<Task>
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MainDatabase.getDatabase(this)

        enableEdgeToEdge()
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText = viewBinding.writingplace
        val addButton = viewBinding.AddButton


        taskList = mutableListOf()
        getAllTasks()
        taskAdapter = TaskAdapter(taskList,
            onUpdate = { position ->
            val newTaskName: String = editText.text.toString()
            if (newTaskName.isNotEmpty()) {
                taskAdapter.updateTask(position, newTaskName)
                updateTaskDatabase(Task(taskList[position].id,newTaskName))
                editText.text.clear()
            }
        },
            onDelete = { taskName -> println(taskName.name)
                deleteTaskDatabase(taskName)
            }
        )

        viewBinding.mainRecView.adapter = taskAdapter
        viewBinding.mainRecView.layoutManager = LinearLayoutManager(this)


        addButton.setOnClickListener() {
                if (editText.text.isNotEmpty()) {
                    val task = Task(0,editText.text.toString())
                    taskList.add(task)
                    insertAll(task)
                    taskAdapter.notifyItemInserted(taskList.size - 1)
                    editText.text.clear()
                }
            }
        }

    private fun deleteTaskDatabase(taskName: Task) {

        lifecycleScope.launch(Dispatchers.IO) {
            db.taskDao().delete(taskName)
        }
    }
    private fun insertAll(taskName: Task){
        lifecycleScope.launch(Dispatchers.IO) {
            db.taskDao().insertAll(taskName)
        }
    }
    private fun getAllTasks(){
        lifecycleScope.launch(Dispatchers.IO) {
            taskList.addAll(db.taskDao().getAll())
        }
    }
    private fun updateTaskDatabase(task:Task){
        lifecycleScope.launch(Dispatchers.IO) {
            db.taskDao().updateTask(task)
        }

    }
}