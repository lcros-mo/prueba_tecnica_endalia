package com.example.endalia.view.fragment.userList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.endalia.R
import com.google.android.material.imageview.ShapeableImageView

class UserRecyclerViewAdapter(private val userList : ArrayList<Users>) :
    RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = userList[position]

        holder.setUpView(user)
    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setUpView(user: Users){
            val portrait : ShapeableImageView = itemView.findViewById(R.id.portrait)
            val lastname : TextView = itemView.findViewById(R.id.lastName)
            val name : TextView = itemView.findViewById(R.id.name)
            val position :TextView = itemView.findViewById(R.id.position)


            portrait.setImageResource(user.portrait)
            lastname.text = user.lastName
            name.text = user.name
            position.text = user.position
        }

    }
}
