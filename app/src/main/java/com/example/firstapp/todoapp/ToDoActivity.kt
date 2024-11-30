package com.example.firstapp.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoActivity : AppCompatActivity() {
    private val categories = listOf<TaskCategory>( // lista de categorias
        TaskCategory.Other,
        TaskCategory.Business,
        TaskCategory.Personal
    )
    private val tasks = mutableListOf<Task>( // lista de tareas que hacer
        Task("Prueba", TaskCategory.Business),
        Task("Prueba2", TaskCategory.Personal),
        Task("Prueba3", TaskCategory.Other)
    )
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CagetoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var favAddTask: FloatingActionButton

    private lateinit var rvTasks: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_to_do)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initUI()
        initListeners()
    }

    private fun initComponents() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        favAddTask = findViewById<FloatingActionButton>(R.id.favAddTask)
    }
    private fun initUI() {
        categoriesAdapter = CagetoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this) // como el segundo recycleview es horizontal no hace falta asignarle el linearlayoutmanager.horizontal y false
        rvTasks.adapter = tasksAdapter
    }

    private fun initListeners() {
        favAddTask.setOnClickListener{
            showDialog()
        }
    }
    private fun showDialog(){
        val dialog = Dialog(this)//Creamos la ventana pop up
        dialog.setContentView(R.layout.dialog_tasks) // le seteamos el layout creado previamente
        dialog.show() // lo mostramos

        val btnAddTask:Button = dialog.findViewById(R.id.btnAddTask) //DEL DIALOGO, buscamos por id los componentes como el boton y el campo de entrada de texto
        val editTask: EditText = dialog.findViewById(R.id.editTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories) // rg = radiusGroup , contenedor de checkboxs circulares unicos

        btnAddTask.setOnClickListener{ //evento al boton
            val selectedId = rgCategories.checkedRadioButtonId //obtenemos el id del checkbox seleccionado
            val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId) // lo buscamos
            val currentCategory: TaskCategory = when(selectedRadioButton.text){ //hacemos una constante con el valor que contenga el selectedRadioButton
                "Negocios" -> TaskCategory.Business // si es "Negocios se le asigna negocios"
                "Personal" -> TaskCategory.Personal
                else -> TaskCategory.Other
            }
            tasks.add(Task(editTask.text.toString() ,currentCategory)) // a la lista previamente creada o inicializada vacia, se le agrega un objeto Task con los valores puestos en el dialog
            updateTask() //hay que mandarle la informacion al adaptador para que realice los cambios necesarios, por el contrario no mostraria las nuevas tasks
            dialog.hide()//escondemos el dialogo

        }
    }
    private fun updateTask(){
        tasksAdapter.notifyDataSetChanged()
    }
}
