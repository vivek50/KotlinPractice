package com.example.listviewexample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.listviewexample.MainActivity.MyItem
import kotlinx.android.synthetic.main.child_layout_list.view.*

/**
 * Created by vivek50 on 25/3/18.
 */

class MyAdapter(private var context : Context, private var list : ArrayList<MyItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View? {

        val holder : ViewHolder
        val view : View?

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.child_layout_list, null)
            holder = ViewHolder()
            holder.img = view.image as ImageView
            holder.txtTitle = view.txtView as TextView
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val item : MyItem = list[position]
        holder.img.setImageResource(item.imageId!!)
        holder.txtTitle.text = item.title

        return view
    }

    private inner class ViewHolder {

        lateinit var txtTitle : TextView
        lateinit var img : ImageView
    }
}