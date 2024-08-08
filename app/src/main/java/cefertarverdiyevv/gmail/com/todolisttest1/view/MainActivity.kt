package cefertarverdiyevv.gmail.com.todolisttest1.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cefertarverdiyevv.gmail.com.todolisttest1.R
import cefertarverdiyevv.gmail.com.todolisttest1.data.Task
import cefertarverdiyevv.gmail.com.todolisttest1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var TaskAdapter:TaskAdapter
    private lateinit var TaskList:MutableList<Task>
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        TaskList = mutableListOf()
        TaskAdapter = TaskAdapter(TaskList) { position ->
            val newTaskName: String = editText.text.toString()
            if(newTaskName.isNotEmpty()){
                TaskAdapter.updateTask(position, newTaskName)
                editText.text.clear()
            }

        }
        viewBinding.mainRecView.adapter = TaskAdapter
        viewBinding.mainRecView.layoutManager = LinearLayoutManager(this)



            addButton.setOnClickListener() {
                if (editText.text.isNotEmpty()) {
                    val task = Task(editText.text.toString())
                    TaskList.add(task)
                    TaskAdapter.notifyItemInserted(TaskList.size - 1)
                    editText.text.clear()
                }
            }


        }
}