package com.example.gsonexample

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json : String? = readJson()
        if (json != null) {
            val obj : MyObj = Gson().fromJson(json, MyObj::class.java)
            setJsonData(obj)
        }
    }

    private fun readJson() : String? {

        val builder = StringBuilder()

        return try {
            val iStream : InputStream = assets.open("demo.json")
            while (true) {
                val i : Int = iStream.read()
                if (i == -1)
                    break
                else
                    builder.append(i.toChar())
            }
            builder.toString()
        } catch (e : Exception) {
            e.printStackTrace()
            null
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setJsonData (obj : MyObj) {

        txtName.text = obj.name
        txtAge.text = obj.age.toString() + " years"
        txtState.text = obj.state
        txtDesi.text = obj.professionInfo.designation
        txtCompany.text = obj.professionInfo.companyName
        txtEmail.text = obj.contactInfo.emailId
        txtMobile.text = obj.contactInfo.mobileNo

        spiCity.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item,
                obj.cities)
    }

    class MyObj(val name : String, val age : Int, val isIndian : Boolean, val state : String,
                val cities : ArrayList<String>, var contactInfo : ContactObj,
                var professionInfo : ProfessionObj)

    class ContactObj(val emailId : String, val mobileNo : String)

    class ProfessionObj(val designation : String, val companyName : String)
}
