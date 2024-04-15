package com.test.whtsapautoreply.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import com.test.whtsapautoreply.databinding.MessageItemBinding

class MessageListAdapter(private val context: Context, private var messages: List<Msgdb>?) : RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messages?.size!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(messages?.get(position) !!)
    }

    fun setMessages(messages: List<Msgdb>) {
        this.messages = messages
        notifyDataSetChanged() // Notify adapter that the dataset has changed
    }

    class ViewHolder(private val binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(msgdb: Msgdb) {
            binding.apply {
                textTitle.text = msgdb.title
                textDescription.text = msgdb.description
                // Bind other views here
            }
        }

    }
}