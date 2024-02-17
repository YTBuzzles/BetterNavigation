package com.example.betternavigation

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.betternavigation.R.drawable.ic_launcher_background


class SettingsActivity : AppCompatActivity() {

    val settingsList = arrayOf("Apple", "Crapple", "Gapple")
    val settingsLogos =
        arrayOf(ic_launcher_background, ic_launcher_background, ic_launcher_background)
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val settingsList = arrayOf("Apple", "Crapple", "Gapple")
        val settingsLogos =
            arrayOf(ic_launcher_background, ic_launcher_background, ic_launcher_background)
        lateinit var listView: ListView
        listView = findViewById(R.id.settings_list)
        var context = baseContext
        val settingsAdapter = SettingsAdapter(context, settingsList, settingsLogos)
        listView.setAdapter(settingsAdapter)
    }
}
