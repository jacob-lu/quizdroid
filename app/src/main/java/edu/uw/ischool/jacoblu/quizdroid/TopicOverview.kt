package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent.getIntent
import android.os.Bundle
import android.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TopicOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
        val TopicDescription = intent.getStringExtra("TopicDescription")
        val transaction = fragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("TopicDescription", TopicDescription)
        bundle.putString("Topic", intent.getStringExtra("Topic"))
        val fragmentTopic = FragmentTopic()
        fragmentTopic.arguments = bundle
        transaction.add(R.id.fragmentHolder, fragmentTopic).commit()
        }
}