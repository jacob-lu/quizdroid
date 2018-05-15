package edu.uw.ischool.jacoblu.quizdroid

import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FragmentPreferences : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_preferences_page, container, false) as View
        val button = view.findViewById<Button>(R.id.button)
        val editURL = view.findViewById<EditText>(R.id.editText)
        val timeInterval = view.findViewById<EditText>(R.id.editText2)

        button.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("URL",editURL.text.toString())
            intent.putExtra("Interval", timeInterval.text.toString())
            startActivity(intent)
        }
        return view
    }


}