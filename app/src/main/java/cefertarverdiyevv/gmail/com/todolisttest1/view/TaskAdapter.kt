package cefertarverdiyevv.gmail.com.todolisttest1.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cefertarverdiyevv.gmail.com.todolisttest1.R
import cefertarverdiyevv.gmail.com.todolisttest1.data.Task

class TaskAdapter(private val tasks:MutableList<Task>,
                    private val onUpdate:(Int) -> Unit,
                    private val onDelete:(Task)->Unit
    ):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


        private fun deleteTask(position: Int){
            tasks.removeAt(position)
            notifyItemRemoved(position)
        }

        class TaskViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
            val taskName:TextView = itemView.findViewById(R.id.rectextview)
            val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)
            val updateButton: ImageView = itemView.findViewById(R.id.update_button)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.textview,parent,false)
        return TaskViewHolder(view)
   }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskName.text = tasks[position].name
        holder.deleteButton.setOnClickListener{
            onDelete(tasks[position])
            deleteTask(holder.adapterPosition)
            }
        holder.updateButton.setOnClickListener{
            onUpdate(position)
        }
        }
    fun updateTask(position: Int, newTask: String){
        tasks[position].name = newTask
        notifyItemChanged(position)
    }

    }
