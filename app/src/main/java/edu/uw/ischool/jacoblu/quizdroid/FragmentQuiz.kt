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
        val mathanswers = arrayOf("2 * 2?" to "4", "3 * 3?" to "9", "5 * 4?" to "20")
        val physicsanswers = arrayOf("What is Gravity?" to "g", "What is Force?" to "F", "What is mass?" to "m")
        val marvelanswers = arrayOf("Who has deadpool in their name?" to "Deadpool",
                "What insect bit Spiderman?" to "Spider", "Is Ironman rich?" to "Yes")
        val mathchoices = mapOf(0 to arrayOf("3","4","5","6"), 1 to arrayOf("5","7","9","20"), 2 to arrayOf("1","20","24","23"))
        val physicschoices = mapOf(0 to arrayOf("h","j","g","e"), 1 to arrayOf("G","Q","E","F"), 2 to arrayOf("m","v","p","q"))
        val marvelchoices = mapOf(0 to arrayOf("Thor","ET","Spiderman","Deadpool"), 1 to arrayOf("Balloon","Shark","Dog","Spider"), 2 to arrayOf("No","Maybe","He","Yes"))


        val submitbutton = view.findViewById(R.id.SubmitButton) as Button
        val radiobutton1 = view.findViewById<RadioButton>(R.id.radioButton1)
        val radiobutton2 = view.findViewById<RadioButton>(R.id.radioButton2)
        val radiobutton3 = view.findViewById<RadioButton>(R.id.radioButton3)
        val radiobutton4 = view.findViewById<RadioButton>(R.id.radioButton4)
        val buttons : Array<RadioButton> = arrayOf(radiobutton1,radiobutton2,radiobutton3,radiobutton4)
        val topic = arguments!!.getString("Topic")
        var pagenumber = arguments!!.getInt("PageNumber")
        var numbercorrect = arguments!!.getInt("NumberCorrect")
        fun setButtonText(array: Array<String>) {
            buttons[0].setText(array[0])
            buttons[1].setText(array[1])
            buttons[2].setText(array[2])
            buttons[3].setText(array[3])
        }
        var answerKey:String = ""
        // check what topic it is, set questions according to page number
        if(topic.equals("Math")) {
            if(pagenumber == 0){
                textView.setText(mathanswers[0].first)
                answerKey = (mathanswers[0].second)
                setButtonText(mathchoices.get(0)!!)
            } else if (pagenumber == 1) {
                textView.setText(mathanswers[1].first)
                answerKey = (mathanswers[1].second)
                setButtonText(mathchoices.get(1)!!)
            } else {
                textView.setText(mathanswers[2].first)
                answerKey = (mathanswers[2].second)
                setButtonText(mathchoices.get(2)!!)
            }
        } else if (topic.equals("Physics")) {
            if(pagenumber == 0){
                textView.setText(physicsanswers[0].first)
                answerKey = (physicsanswers[0].second)
                setButtonText(physicschoices.get(0)!!)
            } else if (pagenumber == 1) {
                textView.setText(physicsanswers[1].first)
                answerKey = (physicsanswers[1].second)
                setButtonText(physicschoices.get(1)!!)
            } else {
                textView.setText(physicsanswers[2].first)
                answerKey = (physicsanswers[2].second)
                setButtonText(physicschoices.get(2)!!)
            }
        } else {
            if(pagenumber == 0){
                textView.setText(marvelanswers[0].first)
                answerKey = (marvelanswers[0].second)
                setButtonText(marvelchoices.get(0)!!)
            } else if (pagenumber == 1) {
                textView.setText(marvelanswers[1].first)
                answerKey = (marvelanswers[1].second)
                setButtonText(marvelchoices.get(1)!!)
            } else {
                textView.setText(marvelanswers[2].first)
                answerKey = (marvelanswers[2].second)
                setButtonText(marvelchoices.get(2)!!)
            }
        }

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