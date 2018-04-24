package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {


    val topics = arrayOf("Math", "Physics", "Marvel Super Heroes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.ListView)
        val adapter = ArrayAdapter<String> (this, android.R.layout.simple_list_item_2, android.R.id.text1, topics)
        listView.adapter = adapter
        //do something in response to click

        val clickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            if (position == 0) {
                val intent: Intent = Intent(applicationContext, MathTopicOverview::class.java)
                startActivity(intent)
            } else if (position == 1) {
                val intent: Intent = Intent(applicationContext, PhysicsTopicOverview::class.java)
                startActivity(intent)
            } else {
                val intent: Intent = Intent(applicationContext, MarvelSuperHeroTopicOverview::class.java)
                startActivity(intent)
            }
        }
        listView.setOnItemClickListener(clickListener)
    }
}
