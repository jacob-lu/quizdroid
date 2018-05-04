package edu.uw.ischool.jacoblu.quizdroid

import android.os.Bundle
import android.app.Fragment
import android.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class FragmentTopic : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_topic_overview, container, false) as View
        val BeginButton = view.findViewById(R.id.BeginButton) as Button
        val TopicDescription :String = QuizApp.getInfo.getTopics().get(arguments!!.getString("Topic"))!!.longD
        val topic = view.findViewById<TextView>(R.id.TopicDescription)
        topic.setText(TopicDescription)
        BeginButton.setOnClickListener {
            val fragmentQuiz : Fragment = FragmentQuiz()
            val transaction = fragmentManager.beginTransaction()
            transaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN )
            val bundle = Bundle()
            fragmentQuiz.arguments = bundle
            bundle.putInt("PageNumber", 0)
            bundle.putInt("NumberCorrect", 0)
            bundle.putString("Topic", QuizApp.getInfo.getTopics().get(arguments!!.getString("Topic"))!!.title)
            transaction.replace(R.id.fragmentHolder, fragmentQuiz).commit()
        }
        return view
    }

}