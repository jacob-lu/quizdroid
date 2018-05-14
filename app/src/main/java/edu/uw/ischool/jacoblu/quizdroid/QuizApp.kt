package edu.uw.ischool.jacoblu.quizdroid

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.util.Log
import org.json.JSONArray
import java.io.File
import java.io.FileInputStream

class QuizApp: Application(), TopicRepository {
    companion object {
        lateinit var getInfo : QuizApp
        private set
        lateinit var topics : Map<String,Quiz>
        private set
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("QuizApp", "I am being run!")
        val sdcard = Environment.getExternalStorageDirectory()
        val specificfile= File(sdcard, "extracredquestions.json")
        val inputStream = FileInputStream(specificfile)
        val inputString = inputStream.bufferedReader().use {
            it.readText()
        }

        val array = JSONArray(inputString)
        //iterate through objects
        val quizzes = mutableMapOf<String,Quiz>()
        for (i in 0..(array.length() - 1)) {
            val jsonobject = array.getJSONObject(i)
            val topic = jsonobject.getString("title")
            val description = jsonobject.getString("desc")
            val quiz = jsonobject.getJSONArray("questions")
            //iterate through each question
            val storeQuestionsArray = ArrayList<Question>()
            for(l in 0..(quiz.length() - 1)) {
                val question = quiz.getJSONObject(l)
                val questionString = question.getString("text")
                val answerIndex = question.getInt("answer") - 1
                val answers = question.getJSONArray("answers")
                val storeAnswersArray = Array<String>(answers.length()) { "answers" }
                // iterate through answers of each question
                for(j in 0..answers.length()-1) {
                    storeAnswersArray[j] = (answers[j].toString())
                }
                val AddQuestion = Question(questionString, storeAnswersArray, answerIndex)
                storeQuestionsArray.add(AddQuestion)
            }
            val storeQuiz = Quiz(topic, description, description, storeQuestionsArray)
            quizzes.put(topic,storeQuiz)

        }
        getInfo = this
        topics = quizzes

    }

    override fun getTopics(): Map<String,Quiz> {
        return topics
    }

    override fun getQuestion(key: String, questionNumber : Int): Question? {
        return topics.get(key)!!.questions[questionNumber]
    }
}

data class Question (val question: String, val answers: Array<String>, val answerInteger: Int)
data class Quiz (val title:String, val shortD: String, val longD: String, val questions: ArrayList<Question>)

interface TopicRepository {
    fun getTopics(): Map<String,Quiz>
    fun getQuestion(key: String, questionNumber: Int): Question?
}
