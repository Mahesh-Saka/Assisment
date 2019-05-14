package com.greenlight.assisgment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.assisgment.R
import com.greenlight.assisgment.callback.OnItemClickListener
import com.greenlight.assisgment.databinding.LayoutRecycleRowBinding
import com.greenlight.assisgment.entity.UserModel

class UserListAdapter(var list: ArrayList<UserModel>, var onCardItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycle_row, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.layoutRecycleRowBinding!!.`object` = list[position]
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var layoutRecycleRowBinding: LayoutRecycleRowBinding? = null

        init {
            layoutRecycleRowBinding = DataBindingUtil.bind(view)
            layoutRecycleRowBinding!!.root.setOnClickListener {
                onCardItemClickListener.onCardItemSelected(list[adapterPosition], adapterPosition)

            }
        }
    }

    fun addAll(list: ArrayList<UserModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun notifyItemRemove(position: Int) {
        if (list.size > 0) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}