package com.example.listviewexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var list: ArrayList<MyItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        list.clear()
        list.add(MyItem(1, R.mipmap.ic_launcher, "1.0"))
        list.add(MyItem(2, R.mipmap.ic_launcher, "1.1"))
        list.add(MyItem(3, R.mipmap.ic_launcher, "Cupcake"))
        list.add(MyItem(4, R.mipmap.ic_launcher, "Donut"))
        list.add(MyItem(5, R.mipmap.ic_launcher, "Eclair"))
        list.add(MyItem(6, R.mipmap.ic_launcher, "Froyo"))
        list.add(MyItem(7, R.mipmap.ic_launcher, "Gingerbread"))
        list.add(MyItem(8, R.mipmap.ic_launcher, "Honeycomb"))
        list.add(MyItem(9, R.mipmap.ic_launcher, "IceCreamSandwich"))
        list.add(MyItem(10, R.mipmap.ic_launcher, "KitKat"))
        list.add(MyItem(11, R.mipmap.ic_launcher, "Lollipop"))
        list.add(MyItem(12, R.mipmap.ic_launcher, "Marshmallow"))
        list.add(MyItem(13, R.mipmap.ic_launcher, "Nougat"))
        list.add(MyItem(14, R.mipmap.ic_launcher, "Oreo"))
        list.add(MyItem(15, R.mipmap.ic_launcher, "Android P"))
        setAdapter()
    }

    private fun setAdapter() {

        val adapter = MyAdapter(this@MainActivity, list)
        listView.adapter = adapter
    }

    class MyItem(val id: Int, val imageId: Int?, var title: String?)
}
