package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {


    val topics = arrayOf( "Marvel Super Heroes", "Math", "Physics")
    val topicDescriptions = arrayOf("Marvel Super Heroes are a cartoon series originated in the U.S",
            "Math is using numbers to find significant calculations","Physics is finding out about how the world works around us")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.ListView)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, topics)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, v, position, id ->
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("TopicDescription", topicDescriptions[position])
            intent.putExtra("Topic", topics[position])
            startActivity(intent)
        }
    }

}
