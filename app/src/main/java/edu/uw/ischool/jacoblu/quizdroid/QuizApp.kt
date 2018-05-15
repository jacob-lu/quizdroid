package edu.uw.ischool.jacoblu.quizdroid
import android.app.Application
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.io.*


class QuizApp : Application() {
    companion object {
        lateinit var getInfo: StoredApp
        private set
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "quiz app works!")
        getInfo = StoredApp()
    }

}

class StoredApp: AsyncTask<String,String,String>, TopicRepository{
    private var topics: Map<String,Quiz> ?= null
    var url = ""

    constructor() {
        url = "http://tednewardsandbox.site44.com/questions.json"
        execute(url).get()
    }

    override fun doInBackground(vararg p0: String?): String  {

        // code borrowed from https://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        try {
            val sdcard = Environment.getExternalStorageDirectory()
            val url = URL("http://tednewardsandbox.site44.com/questions.json")
            val connection = url.openConnection() as HttpURLConnection
            val file = FileOutputStream(File(sdcard, "questions.json"))
            val inputstream : InputStream = connection.getInputStream()
            val buffer = ByteArray(1024)
            var len1 = inputstream.read(buffer)
            while (len1 > 0) {
                file.write(buffer, 0, len1)
                len1 = inputstream.read(buffer)
            }
            file.close()
        } catch (e: Exception) {
            Log.d("MainActivity", "Error: Download fails or is interrupted")
        }

            val connection = URL(url).openConnection() as HttpURLConnection
            val inputString = BufferedInputStream(connection.inputStream).use { it.reader().use { it.readText() } }

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
        topics = quizzes
        return inputString
    }

    override fun getTopics(): Map<String,Quiz> {
        return topics!!
    }

    override fun getQuestion(key: String, questionNumber : Int): Question? {
        return topics!!.get(key)!!.questions[questionNumber]
    }
}



data class Question (val question: String, val answers: Array<String>, val answerInteger: Int)
data class Quiz (val title:String, val shortD: String, val longD: String, val questions: ArrayList<Question>)

interface TopicRepository {
    fun getTopics(): Map<String,Quiz>
    fun getQuestion(key: String, questionNumber: Int): Question?
}


