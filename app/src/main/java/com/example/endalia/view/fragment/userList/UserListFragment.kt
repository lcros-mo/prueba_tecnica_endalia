package com.example.endalia.view.fragment.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endalia.R
import kotlin.random.Random

private lateinit var adapter: UserRecyclerViewAdapter
private lateinit var recyclerView : RecyclerView
private lateinit var newArrayList : ArrayList<Users>

lateinit var portraitId : Array<Int>
lateinit var lastName : Array<String>
lateinit var  name : Array<String>
lateinit var  position : Array<String>
class UserListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = UserRecyclerViewAdapter(newArrayList)
        recyclerView.adapter = adapter

        newArrayList.sortBy { it.lastName }
    }

    private fun dataInitialize(){
        newArrayList = arrayListOf<Users>()

        portraitId = arrayOf(
            R.drawable.employee_portrait_1,
            R.drawable.employee_portrait_2,
            R.drawable.employee_portrait_3,
            R.drawable.employee_portrait_4,
            R.drawable.employee_portrait_5,
            R.drawable.employee_portrait_6,
            R.drawable.employee_portrait_7,
            R.drawable.employee_portrait_8,
            R.drawable.employee_portrait_9,
            R.drawable.employee_portrait_10,
            R.drawable.employee_portrait_11,
            R.drawable.employee_portrait_12,
            R.drawable.employee_portrait_13,
            R.drawable.employee_portrait_14,
            R.drawable.employee_portrait_15,
            R.drawable.employee_portrait_16,
            R.drawable.employee_portrait_17,
            R.drawable.employee_portrait_18,
            R.drawable.employee_portrait_19,
            R.drawable.employee_portrait_20
        )

        lastName = arrayOf(
            "Miramonte Dominguez",
            "Garcia Perez",
            "Hernandez Torres",
            "Gonzalez Rodriguez",
            "Lopez Sanchez",
            "Gomez Fernandez",
            "Jimenez Ruiz",
            "Moreno Diaz",
            "Muñoz Castro",
            "Alvarez Romero",
            "Ruiz Serrano",
            "Gutierrez Navarro",
            "Torres Flores",
            "Diaz Ortega",
            "Martin Bravo",
            "Castillo Medina",
            "Ramos Soler",
            "Rubio Marin",
            "Santos Gallego",
            "Ortiz Garrido"
        )

        name = arrayOf(
            "Carlos",
            "Ana",
            "Pedro",
            "Maria",
            "Javier",
            "Elena",
            "Pablo",
            "Laura",
            "Sergio",
            "Isabel",
            "Miguel",
            "Cristina",
            "Antonio",
            "Lucia",
            "David",
            "Beatriz",
            "Raquel",
            "Alberto",
            "Nuria",
            "Diego"
        )

        position = arrayOf(
            "Tecnico de RRHH",
            "Contable",
            "Abogado",
            "Analista de sistemas",
            "Ingeniero de software",
            "Diseñador gráfico",
            "Chef",
            "Médico",
            "Enfermero",
            "Farmacéutico",
            "Psicólogo",
            "Profesor",
            "Periodista",
            "Economista",
            "Administrativo",
            "Comercial",
            "Arquitecto",
            "Electricista",
            "Mecánico",
            "Fontanero"
        )

        for (i in 0..100){

            val random = Random.nextInt(20)
            val random1 = Random.nextInt(20)
            val random2 = Random.nextInt(20)
            val random3 = Random.nextInt(20)
            val users = Users(portraitId[random], lastName[random1], name[random2], position[random3])
            newArrayList.add(users)
        }
    }
}