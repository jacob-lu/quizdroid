package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MarvelSuperHeroTopicOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marvel_super_hero_topic_overview)
        val BeginButton = findViewById(R.id.BeginButton) as Button

        BeginButton.setOnClickListener {
            val intent : Intent = Intent(applicationContext, MarvelSuperHeroQuestion1::class.java)
            Log.i("mainactivity", "dog")
            startActivity(intent)
        }
    }
}
