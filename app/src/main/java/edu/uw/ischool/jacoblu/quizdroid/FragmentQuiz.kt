package edu.uw.ischool.jacoblu.quizdroid

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class FragmentQuiz : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_question_page, container, false) as View
        val SubmitButton = view.findViewById<Button>(R.id.SubmitButton)
        val textView = view.findViewById<TextView>(R.id.textView)
        val submitbutton = view.findViewById(R.id.SubmitButton) as Button
        val radiobutton1 = view.findViewById<RadioButton>(R.id.radioButton1)
        val radiobutton2 = view.findViewById<RadioButton>(R.id.radioButton2)
        val radiobutton3 = view.findViewById<RadioButton>(R.id.radioButton3)
        val radiobutton4 = view.findViewById<RadioButton>(R.id.radioButton4)
        val buttons : Array<RadioButton> = arrayOf(radiobutton1,radiobutton2,radiobutton3,radiobutton4)

        val topic = arguments!!.getString("Topic")
        var pagenumber = arguments!!.getInt("PageNumber")
        var numbercorrect = arguments!!.getInt("NumberCorrect")

        //setting radio button text
        fun setButtonText(array: Array<String>) {
            buttons[0].setText(array[0])
            buttons[1].setText(array[1])
            buttons[2].setText(array[2])
            buttons[3].setText(array[3])
        }
        var answerKey:String = ""

        //populate the xml with question, answers, and set up answerkey
        fun setText(pageNumber: Int) {
            textView.setText(QuizApp.getInfo.getQuestion(topic, pageNumber)!!.question)
            val correctAnswer = QuizApp.getInfo.getQuestion(topic, pageNumber)!!.answerInteger
            answerKey = (QuizApp.getInfo.getQuestion(topic, pageNumber)!!.answers[correctAnswer])
            setButtonText(QuizApp.getInfo.getQuestion(topic, pageNumber)!!.answers)
        }
        setText(pagenumber)


        var useranswer: String = ""
        for(btn in buttons) {
            btn.setOnClickListener {
                if (radiobutton1.isChecked()) {
                    useranswer = radiobutton1.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                } else if (radiobutton2.isChecked()) {
                    useranswer = radiobutton2.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                } else if (radiobutton3.isChecked()) {
                    useranswer = radiobutton3.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                } else {
                    useranswer = radiobutton4.getText().toString()
                    submitbutton.setVisibility(View.VISIBLE)
                }
            }}

        SubmitButton.setOnClickListener {
            if(useranswer.equals(answerKey)) {
                numbercorrect++
            }
            pagenumber++
            val fragmentAnswer : Fragment = FragmentAnswer()
            val transaction = fragmentManager.beginTransaction()
            transaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN )
            val bundle = Bundle()
            fragmentAnswer.arguments = bundle
            bundle.putInt("PageNumber", pagenumber)
            bundle.putInt("NumberCorrect", numbercorrect)
            bundle.putString("UserAnswer", useranswer)
            bundle.putString("CorrectAnswer", answerKey)
            bundle.putString("Topic", topic)
            transaction.replace(R.id.fragmentHolder, fragmentAnswer).commit()
        }
        return view
    }

}