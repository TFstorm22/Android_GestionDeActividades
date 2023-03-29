package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityLoginBinding
import java.text.SimpleDateFormat
import java.util.*

// first create adapter class. This inherits recycler view. Recycler view now requires view holder
class TodoAdapter(val list: List<TodoModel>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    // 3 functions of the view holder
    // 1st func
    // In this Layout inflatter is called which converts view in such a form that adapter can consume it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        )
    }


    override fun getItemCount() = list.size

    // 2nd func
    // this will set data in each card
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position]) // we are passing the object of the list that we made in the ToDoModel.kt
    }

    // 3rd func
    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    // view holder is present inside the recycler view
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var vistaBinding: ActivityLoginBinding
        fun bind(todoModel: TodoModel) {


            with(itemView) {
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[Random().nextInt(colors.size)]
                itemView.findViewById<View>(R.id.viewColorTag).setBackgroundColor(randomColor)
                itemView.findViewById<TextView>(R.id.txtShowTitulo).text = todoModel.title
                itemView.findViewById<TextView>(R.id.txtShowTarea).text = todoModel.description
                itemView.findViewById<TextView>(R.id.txtShowCategoria).text = todoModel.category


                updateTime(todoModel.time)
                updateDate(todoModel.date)

            }
        }
        private fun updateTime(time: Long) {
            //Mon, 5 Jan 2020
            val myformat = "h:mm a"
            val sdf = SimpleDateFormat(myformat)
            itemView.findViewById<TextView>(R.id.txtShowHora).text =sdf.format(Date(time))

        }

        private fun updateDate(time: Long) {
            //Mon, 5 Jan 2020
            val myformat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            itemView.findViewById<TextView>(R.id.txtShowFecha).text =sdf.format(Date(time))

        }
    }

}
