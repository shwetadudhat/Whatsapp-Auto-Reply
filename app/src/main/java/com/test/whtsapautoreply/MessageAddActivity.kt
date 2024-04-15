package com.test.whtsapautoreply

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import com.test.whtsapautoreply.ViewModel.MsgViewModel
import com.test.whtsapautoreply.databinding.ActivityMessageAddBinding


class MessageAddActivity : AppCompatActivity() {

    private lateinit var msgViewModel: MsgViewModel

    private lateinit var binding: ActivityMessageAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = (application as MyApplication).msgRepository

        msgViewModel = ViewModelProvider(this, MsgViewModelFactory(repository)).get(MsgViewModel::class.java)

        // Setup click listener for submit button
        binding.btnSubmit.setOnClickListener {
            messageAdd()
        }

    }

    private fun messageAdd() {
        if (binding.etName.text.toString()?.equals("")!!){
            return
        }else if(binding.etMsg.text?.equals("")!!)
            return
        else{
            val msg = Msgdb(title=binding.etName.text.toString(),description=binding.etMsg.text.toString())
            msgViewModel.insert(msg)
        }

        setResult(Activity.RESULT_OK) // Set result OK to indicate success
        finish() // Finish this activity

    }
}