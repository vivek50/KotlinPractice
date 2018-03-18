package com.example.intents

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intent_demo.*

class IntentDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_demo)

        button1.setOnClickListener{

            // normal way
//            val intent : Intent = Intent(this@IntentDemo,
//                    IntentDemo2Activity::class.javaObjectType)
//            startActivity(intent)

            // inline code
//            startActivity(Intent(this@IntentDemo, IntentDemo2Activity::class.javaObjectType))
            // OR you can use
            startActivity(Intent(this@IntentDemo, IntentDemo2Activity::class.java))

            // Here You can use  kotlin::class.java or
            // kotlin::class.javaObjectType it will return same
        }
    }
}
