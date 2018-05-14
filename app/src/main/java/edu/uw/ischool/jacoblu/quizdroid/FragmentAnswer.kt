package edu.uw.ischool.jacoblu.quizdroid

import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class FragmentAnswer : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_answer_page, container, false) as View
        val nextButton = view.findViewById<Button>(R.id.Next)
        val finishButton = view.findViewById<Button>(R.id.Finish)
        val userAnswer = view.findViewById<TextView>(R.id.UserAnswer)
        val correctAnswer = view.findViewById<TextView>(R.id.CorrectAnswer)
        val howManyCorrect = view.findViewById<TextView>(R.id.HowMany)
        userAnswer.setText("Your Answer: " + arguments!!.getString("UserAnswer"))
        //correctAnswer.setText("Correct Answer: " + arguments!!.getString("CorrectAnswer"))
        val correctIndex = QuizApp.getInfo.getQuestion(arguments!!.getString("Topic"), (arguments!!.getInt("PageNumber")-1))!!.answerInteger
        correctAnswer.setText("Correct Answer: " + QuizApp.getInfo.getQuestion(arguments!!.getString("Topic"),
                (arguments!!.getInt("PageNumber"))-1)!!.answers[correctIndex])
        howManyCorrect.setText("You have " + arguments!!.getInt("NumberCorrect").toString() + " out of " +
                QuizApp.getInfo.getTopics().get(arguments!!.getString("Topic"))!!.questions.size+ " correct")
        if(arguments!!.getInt("PageNumber") == QuizApp.getInfo.getTopics().get(arguments!!.getString("Topic"))!!.questions.size) {
            finishButton.setVisibility(View.VISIBLE)
        } else {
            nextButton.setVisibility(View.VISIBLE)
        }
        nextButton.setOnClickListener {
            val fragmentQuiz : Fragment = FragmentQuiz()
            val transaction = fragmentManager.beginTransaction()
            val bundle = Bundle()
            transaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN )
            fragmentQuiz.arguments = bundle
            bundle.putInt("PageNumber", arguments!!.getInt("PageNumber"))
            bundle.putInt("NumberCorrect", arguments!!.getInt("NumberCorrect"))
            bundle.putString("Topic", arguments!!.getString("Topic"))
            transaction.replace(R.id.fragmentHolder, fragmentQuiz).commit()
        }

        finishButton.setOnClickListener{
            val intent : Intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}