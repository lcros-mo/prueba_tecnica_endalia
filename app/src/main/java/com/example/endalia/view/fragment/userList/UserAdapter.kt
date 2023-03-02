package com.example.endalia.view.fragment.userList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.endalia.R
import com.google.android.material.imageview.ShapeableImageView

class UserAdapter(private val userList : ArrayList<Users>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentItem = userList[position]
        holder.portrait.setImageResource(currentItem.portrait)
        holder.lastname.text = currentItem.lastName
        holder.name.text = currentItem.name
        holder.position.text = currentItem.position

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val portrait : ShapeableImageView = itemView.findViewById(R.id.portrait)
        val lastname : TextView = itemView.findViewById(R.id.lastName)
        val name : TextView = itemView.findViewById(R.id.name)
        val position :TextView = itemView.findViewById(R.id.position)
    }
}
