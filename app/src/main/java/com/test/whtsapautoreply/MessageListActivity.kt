package com.test.whtsapautoreply

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.whtsapautoreply.Adapter.MessageListAdapter
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import com.test.whtsapautoreply.ViewModel.MsgViewModel
import com.test.whtsapautoreply.databinding.ActivityMessageAddBinding
import com.test.whtsapautoreply.databinding.ActivityMessageListBinding

class MessageListActivity : AppCompatActivity() {

    private lateinit var msgViewModel: MsgViewModel

    private lateinit var binding: ActivityMessageListBinding

    private lateinit var adapter: MessageListAdapter
    private var messages: List<Msgdb> = emptyList() // Initialize as an empty list

    companion object {
        const val REQUEST_CODE_ADD_MESSAGE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = MessageListAdapter(this,messages)
        binding.rvMsglist.layoutManager = LinearLayoutManager(this)
        binding.rvMsglist.adapter = adapter


        binding.ivAdd.setOnClickListener {
//            intent = Intent(this, MessageAddActivity::class.java)
//            startActivity(intent)

            startActivityForResult(Intent(this, MessageAddActivity::class.java), REQUEST_CODE_ADD_MESSAGE)

        }

        val repository = (application as MyApplication).msgRepository

        msgViewModel = ViewModelProvider(this, MsgViewModelFactory(repository)).get(MsgViewModel::class.java)


        msgViewModel.allItems.observe(this, Observer { items ->
            Log.d("MessageAddActivity", "Received items: ${items.get(0).title}")
            adapter.setMessages(items) // Update adapter with new data
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_MESSAGE && resultCode == Activity.RESULT_OK) {
            msgViewModel.refreshItems() // Trigger refresh of data
        }
    }
}