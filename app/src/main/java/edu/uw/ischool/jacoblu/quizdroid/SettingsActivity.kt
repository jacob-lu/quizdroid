package edu.uw.ischool.jacoblu.quizdroid

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)

        val settingbutton = findViewById<Button>(R.id.button2)
        val cancelbutton = findViewById<Button>(R.id.button3)
        settingbutton.setOnClickListener{
            startActivityForResult(Intent(Settings.ACTION_SETTINGS), 0);
        }
    }
}