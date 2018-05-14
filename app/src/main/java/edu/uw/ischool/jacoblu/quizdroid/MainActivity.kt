package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val listView = findViewById<ListView>(R.id.ListView)
        var topics1:ArrayList<String> = ArrayList()
        val check = QuizApp.getInfo.getTopics()
        for(topic in check.keys) {
            topics1.add(topic)
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, topics1)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, v, position, id ->
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("Topic", topics1[position])
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            setContentView(R.layout.fragment_main)
            val transaction = fragmentManager.beginTransaction()
            val fragmentPreferences = FragmentPreferences()
            transaction.add(R.id.fragmentHolder, fragmentPreferences)
            transaction.commit()
            true
        }
        else -> {

            super.onOptionsItemSelected(item)
        }
    }

}
