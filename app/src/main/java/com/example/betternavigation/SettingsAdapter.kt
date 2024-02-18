package com.example.betternavigation

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.security.AccessControlContext

class SettingsAdapter(cxt: Context, settingsText: Array<String>, images: Array<Int>) :
    BaseAdapter() {
    var context = cxt
    var listText = settingsText
    var listImages = images
    var inflater = LayoutInflater.from(cxt)

    override fun getCount(): Int {
        return listText.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, view: View, parent: ViewGroup): View {
        var convertView = inflater.inflate(R.layout.activity_settings_list_view, null)
        var txtView: TextView = convertView.findViewById(R.id.textView)
        var imageView: ImageView = convertView.findViewById(R.id.imageIcon)
        txtView.setText(listText[position])
        imageView.setImageResource(listImages[position])
        return convertView
    }
}