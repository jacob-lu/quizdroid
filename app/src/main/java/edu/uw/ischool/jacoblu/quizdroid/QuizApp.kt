package edu.uw.ischool.jacoblu.quizdroid

import android.app.Application
import android.util.Log

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
        val mathq1 = Question("What is 2 * 2?", arrayOf("4","5","6", "7"), 0)
        val mathq2 = Question("What is 3 * 3?", arrayOf("4","5","9", "7"), 2)
        val mathq3 = Question("What is 4 * 6?", arrayOf("24","25","36", "37"), 0)
        val marvelq1 = Question("What hero has pool in its name?",arrayOf("Dog","Superman","Deadpool", "Spidey"),2)
        val marvelq2 = Question("Who is strongest?",arrayOf("Black Widosw","Duckey","Batman", "Thanos"),3)
        val marvelq3 = Question("Who is green and powerful?",arrayOf("Dog","Spider","BlackWidow", "The Hulk"),3)
        val physicsq1 = Question("What is force?",arrayOf("F","G","H", "I"),0)
        val physicsq2 = Question("What is mass?",arrayOf("f","d","m", "z"),2)
        val physicsq3 = Question("What is gravity",arrayOf("x","u","z", "g"),3)
        val mathQuiz = Quiz("Math","insert shortdMath here","insert longdMath here",arrayOf(mathq1, mathq2, mathq3))
        val marvelQuiz = Quiz("Marvel","insert shortDMarvel here","insert longDMarvel here",arrayOf(marvelq1, marvelq2, marvelq3))
        val physicsQuiz = Quiz("Physics","insert shortDPhysics here","insert longDPhysics here",arrayOf(physicsq1, physicsq2, physicsq3))
        val quizzes = mapOf("Math" to mathQuiz,"Marvel" to marvelQuiz,"Physics" to physicsQuiz)
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
data class Quiz (val title:String, val shortD: String, val longD: String, val questions: Array<Question>)

interface TopicRepository {
    fun getTopics(): Map<String,Quiz>
    fun getQuestion(key: String, questionNumber: Int): Question?
}
