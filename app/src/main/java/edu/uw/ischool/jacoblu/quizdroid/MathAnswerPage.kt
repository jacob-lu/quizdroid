package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MathAnswerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_answer_page)
        val nextButton = findViewById(R.id.Next) as Button
        val finishButton = findViewById<Button>(R.id.Finish)
        val userAnswer = findViewById<TextView>(R.id.UserAnswer)
        val correctAnswer = findViewById<TextView>(R.id.CorrectAnswer)
        val howManyCorrect = findViewById<TextView>(R.id.HowMany)

        val extras : Bundle = getIntent().getExtras()
        userAnswer.setText("Your Answer: " + extras.getString("UserAnswer"))
        correctAnswer.setText("Correct Answer: " + extras.getString("ActualAnswer"))
        howManyCorrect.setText("You have " + extras.getInt("NumberCorrect").toString() + " out of " +
        extras.getInt("TotalQuestions").toString() + " correct")
            if(extras.getInt("PageNumber") == extras.getInt("TotalQuestions")) {
                finishButton.setVisibility(View.VISIBLE)
            } else {
                nextButton.setVisibility(View.VISIBLE)
            }
        nextButton.setOnClickListener {
            val intent:Intent
            if (extras.getInt("PageNumber") == 1) {
                intent = Intent(applicationContext, MathQuestion2::class.java)
            } else {
                intent = Intent(applicationContext, MathQuestion3::class.java)
            }
            intent.putExtra("NumberCorrect", extras.getInt("NumberCorrect"))
            intent.putExtra("TotalQuestions", extras.getInt("TotalQuestions"))
            startActivity(intent)
        }
        finishButton.setOnClickListener{
            val intent : Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }


    }
}
