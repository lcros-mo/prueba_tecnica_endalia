package com.example.endalia.view.fragment.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endalia.R

private const val  ARG_PARAM1 ="param1"
private const val  ARG_PARAM2 ="param2"
private lateinit var adapter: UserAdapter
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
        adapter = UserAdapter(newArrayList)
        recyclerView.adapter = adapter
    }

    private fun dataInitialize(){
        newArrayList = arrayListOf<Users>()

        portraitId = arrayOf(
            R.drawable.employee_portrait_1
        )

        lastName = arrayOf(
        "Miramonte Dominguez"
        )

        name = arrayOf(
        "Carlos"
        )

        position = arrayOf(
            "Tecnico de RRHH"
        )

        for (i in portraitId.indices){

            val users =Users(portraitId[i], lastName[i], name[i], position[i])
            newArrayList.add(users)
        }
    }
}


//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        val currentUser = userList[position]
//        holder.profileImageView.setImageResource(currentUser.profileImage)
//        holder.nameTextView.text = currentUser.firstName + " " + currentUser.lastName1 + " " + currentUser.lastName2
//        holder.jobTitleTextView.text = currentUser.jobTitle
//    }
//
//    override fun getItemCount() = userList.size
//
//    inner class UserViewHolder(itemView: View) : fragment_user_list.ViewHolder(itemView) {
//        val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
//        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
//        val jobTitleTextView: TextView = itemView.findViewById(R.id.jobTitleTextView)
//    }
