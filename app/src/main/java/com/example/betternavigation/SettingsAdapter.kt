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

class SettingsAdapter() : BaseAdapter(), Parcelable {
    lateinit var context: Context
    lateinit var listText: Array<String>
    lateinit var listImages: Array<Int>
    lateinit var inflater: LayoutInflater

    constructor(cxt: Context, settingsText: Array<String>, images: Array<Int>) : this() {
        context = cxt
        listText = settingsText
        listImages = images
        inflater = LayoutInflater.from(cxt)
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SettingsAdapter> {
        override fun createFromParcel(parcel: Parcel): SettingsAdapter {
            return SettingsAdapter(parcel)
        }

        override fun newArray(size: Int): Array<SettingsAdapter?> {
            return arrayOfNulls(size)
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        var intg: Int 9
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, view: View, parent: ViewGroup): View {
        var convertView = inflater.inflate(R.layout.activity_settings_view, null)
        var txtView: TextView = convertView.findViewById(R.activity_settings_view, null)
        var imageView: ImageView =
    }
}