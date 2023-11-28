package com.example.prim

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.prim.R.*


class MainActivity : AppCompatActivity() {


    class MainActivity : AppCompatActivity() {
        private lateinit var answerTextView2:TextView
        private var a = 0
        private var b = 0
        private var v = ""

        private var chetv = 0
        private var chetn = 0
        private var tot=0
        private var vern = 0
        private var result = 0
        private var correctAnswers = 0
        private var incorrectAnswers = 0


        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

            answerTextView2=findViewById(id.textView2)
            val ButCor: Button =findViewById(id.button2)
            val ButNoCor: Button =findViewById(id.button3)
        ButCor.setOnClickListener {
            checkAnswer(true)
        }
        ButNoCor.setOnClickListener {
            checkAnswer(false)
        }
        generateProblem()
    }

    fun butStart(view: View) {
        val textView: TextView = findViewById(id.textView)

        textView.text = a.toString()+v+b.toString()



        correctAnswers=Calut(a, b, v)
        val isCorrect = (0..1).random() == 1
        if (isCorrect) {
            answerTextView2.text = correctAnswers.toString()
        } else {
            var wrongAnswer = correctAnswers
            while (wrongAnswer == correctAnswers) {
                wrongAnswer = (10..99).random()
            }
            answerTextView2.text = wrongAnswer.toString()
        }

    }




    private fun generateProblem() {
        a = (0..100).random()
        b = (0..100).random()
        val operators = arrayOf("*", "-", "+", "/")
        val ostat=a%b
        v = operators.random()
        if (v == "/" && ostat>0) {
            while (a % b == 0) {
                a = (10..99).random()
                b = (10..99).random()
            }
        }



    }

    private fun checkAnswer(isTrue:Boolean) {

        val textView: TextView = findViewById(id.textView)
        val selectedAnswer = answerTextView2.text.toString().toInt()
        val operands = textView.text.split(v)
        val oper1 = operands[0].toInt()
        val oper2 = operands[1].toInt()
        val correctAnswer = Calut(oper1, oper2, v)



        if (selectedAnswer == correctAnswer && isTrue || selectedAnswer != correctAnswer && !isTrue) {
            chetv++
        } else {
            chetn++
        }

        tot++
        checkResult()
        generateProblem()
    }

    private fun checkResult() {
        val textView4: TextView = findViewById(id.textView4)
        val textView13: TextView = findViewById(id.textView13)
        val textView8: TextView = findViewById(id.textView8)
        val textView10: TextView = findViewById(id.textView10)
        val correctPercentage = if (tot > 0) (chetv.toFloat() / tot) * 100 else 0.0
        val otvet = String.format("%.2f%%", correctPercentage)
        textView13.text=chetv.toString()
        textView8.text=(chetn).toString()
        textView10.text=tot.toString()
        textView4.text = otvet
    }
    fun Calut(oper1: Int, oper2: Int, znak: String): Int {
        return when (znak) {
            "*" -> oper1 * oper2
            "-" -> oper1 - oper2
            "+" -> oper1 + oper2
            "/" -> oper1 / oper2
            else -> 0
        }

    }

    }
}