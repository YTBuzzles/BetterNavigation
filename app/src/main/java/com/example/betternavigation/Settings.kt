package com.example.betternavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.betternavigation.R.drawable.ic_launcher_background


class SettingsActivity : AppCompatActivity() {

    val settingsList = arrayOf("Apple", "Crapple", "Gapple")
    val settingsLogos =
        intArrayOf(ic_launcher_background, ic_launcher_background, ic_launcher_background)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
//            .replace(R.id.setting_1, Settings())
            .commit()
    }
}

class Settings : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}