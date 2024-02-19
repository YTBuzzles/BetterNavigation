package com.example.betternavigation

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceManager

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val home = R.layout.fragment_home
        return inflater.inflate(home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        fab_settings.setOnClickListener {

        findNavController().navigate(R.id.homeFragment)
    }
//        settings()
    }


    private fun settings() {
//        val sp = PreferenceManager.getDefaultSharedPreferences(context)

//        val signature = sp.getString("signature", "")
//        val defaultReplyAction = sp.getString("reply", "")
//        val sync = sp.getBoolean("sync", true)
//        val notifications = sp.getBoolean("notifications", true)
//        val volume = sp.getInt("volume_notifications", 0)
//

//        tv_signature.text = "Signature: $signature"
//        tv_reply.text = "Default reply: $defaultReplyAction"
//        tv_sync.text = "Sync: $sync"
//        tv_notifications.text = "Disable notifications: $notifications"
//
//        pb_volume.setProgress(volume, true)

    }

}