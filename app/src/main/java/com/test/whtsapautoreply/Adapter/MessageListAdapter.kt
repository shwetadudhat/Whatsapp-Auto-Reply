package com.test.whtsapautoreply.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.whtsapautoreply.R
import com.test.whtsapautoreply.RoomDatababse.Msgdb

class MessageListAdapter(private val context: Context, private val messages: List<Msgdb>?) : RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return messages?.size!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        viewHolder.messageTV.text = messages?.get(position)?.message
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        var messageTV: TextView = itemView.findViewById(R.id.messageTextView) as TextView
    }
}