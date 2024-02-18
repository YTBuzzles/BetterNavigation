package com.example.betternavigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.betternavigation.R.drawable.ic_launcher_background
import java.security.AccessController.getContext

class SettingsActivity : AppCompatActivity() {

    val settingsList = arrayOf("Apple", "Crapple", "Gapple")
    val settingsLogos =
        arrayOf(ic_launcher_background, ic_launcher_background, ic_launcher_background)
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var context: Context = baseContext

        Log.d("Settings", "Content_Settingsd")

        listView = findViewById(R.id.settings_list)
        val settingsAdapter = SettingsAdapter(context, settingsList, settingsLogos)
        listView.setAdapter(settingsAdapter)
    }
}
