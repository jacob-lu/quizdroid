package edu.uw.ischool.jacoblu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton

class MarvelSuperHeroQuestion2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_super_hero_question2)

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
                    answer = radiobutton1.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                } else if (radiobutton2.isChecked()) {
                    answer = radiobutton2.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                } else if (radiobutton3.isChecked()) {
                    answer = radiobutton3.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                } else {
                    answer = radiobutton4.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                }
            }}
        submitbutton.setOnClickListener {
            val extras : Bundle = getIntent().getExtras()
            var numberOfCorrect: Int = extras.getInt("NumberCorrect")
            val intent : Intent = Intent(applicationContext, MarvelSuperHeroAnswerPage::class.java)
            intent.putExtra("UserAnswer", answer)
            intent.putExtra("NumberCorrect", numberOfCorrect)
            Log.i("mainactivity", "enter here")

            if(answer.equals(radiobutton4.getText().toString())) {
                numberOfCorrect = numberOfCorrect + 1
                intent.putExtra("NumberCorrect", numberOfCorrect)
            }

            intent.putExtra("TotalQuestions", 3)
            intent.putExtra("PageNumber",2)
            intent.putExtra("ActualAnswer", radiobutton4.getText().toString())
            startActivity(intent)

        }
        }


    }

