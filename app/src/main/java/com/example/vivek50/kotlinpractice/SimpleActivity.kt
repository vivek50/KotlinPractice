package com.example.vivek50.kotlinpractice

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_simple.*
import kotlinx.android.synthetic.main.content_simple.*

@SuppressLint("SetTextI18n")
class SimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setSupportActionBar(toolbar)

        demoToast()
        demoSnackBar()
        setEdtText()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_simple_, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun demoToast() {
//        val b1 = findViewById<Button>(R.id.button1)
//        b1.setOnClickListener {
//            Toast.makeText(this, "Toast Demo", Toast.LENGTH_SHORT).show()
//        }

        // OR Use Like also
        button1.setOnClickListener {
            Toast.makeText(this, "Toast Demo", Toast.LENGTH_LONG).show()
            textView1.text = "Toast Button Clicked"
//            textView1.setText("Toast Button Clicked")
        }
    }

    private fun demoSnackBar() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Snackbar Demo", Snackbar.LENGTH_LONG).show()
            textView1.text = "Fab Clicked"
        }
//        OR Use Like also
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Snackbar Demo", Snackbar.LENGTH_LONG).show()
//        }
    }

    private fun setEdtText() {

        button2.setOnClickListener {
            val str : String = editText1.text.toString()
            textView1.text = str

        }
    }
}
