package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton

class PhysicsQuestion1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_question1)
        val submitbutton = findViewById(R.id.SubmitButton) as Button
        val radiobutton1 = findViewById<RadioButton>(R.id.radioButton1)
        val radiobutton2 = findViewById<RadioButton>(R.id.radioButton2)
        val radiobutton3 = findViewById<RadioButton>(R.id.radioButton3)
        val radiobutton4 = findViewById<RadioButton>(R.id.radioButton4)
        val buttons : Array<RadioButton> = arrayOf(radiobutton1,radiobutton2,radiobutton3,radiobutton4)
        var answer: String = ""
        for(btn in buttons) {
            btn.setOnClickListener {
                if (radiobutton1.isChecked()) {
                    Log.i("mainactivity", "view1")
                    answer = radiobutton1.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                } else if (radiobutton2.isChecked()) {
                    Log.i("mainactivity", "view2")
                    answer = radiobutton2.getText().toString()

                    submitbutton.setVisibility(View.VISIBLE)
                } else if (radiobutton3.isChecked()) {
                    Log.i("mainactivity", "view3")
                    answer = radiobutton3.getText().toString()

                    submitbutton.setVisibility(View.VISIBLE)
                } else {
                    Log.i("mainactivity", "view4")

                    answer = radiobutton4.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                }
        }}
        submitbutton.setOnClickListener {
            val intent : Intent = Intent(applicationContext, PhysicsAnswerPage::class.java)
            var numberOfCorrect: Int = 0
            intent.putExtra("UserAnswer", answer)
            intent.putExtra("NumberCorrect", numberOfCorrect)
            Log.i("mainactivity", "enter here")

            if(answer.equals(radiobutton1.getText().toString())) {
                Log.i("mainactivity", "enterenter")
                numberOfCorrect = numberOfCorrect + 1
                intent.putExtra("NumberCorrect", numberOfCorrect)
            }

            intent.putExtra("TotalQuestions", 3)
            intent.putExtra("ActualAnswer", radiobutton1.getText().toString())
            intent.putExtra("PageNumber",1)
            startActivity(intent)

        }

    }
}
