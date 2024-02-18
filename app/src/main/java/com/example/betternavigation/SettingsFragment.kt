package com.example.betternavigation

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import android.view.View
import android.widget.Toast


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        Toast.makeText(context, "These are your settings", Toast.LENGTH_SHORT).show()
    }
}