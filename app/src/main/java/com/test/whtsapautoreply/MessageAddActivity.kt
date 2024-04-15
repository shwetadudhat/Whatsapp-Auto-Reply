package com.test.whtsapautoreply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_message_add.et_msg
import kotlinx.android.synthetic.main.activity_message_add.et_name

class MessageAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_add)


    }

    fun MessageAdd(view: View) {
        if (et_name.text.toString().equals("")){
            return
        }else if(et_msg.text?.equals("")!!)
            return
        else{

        }
    }
}