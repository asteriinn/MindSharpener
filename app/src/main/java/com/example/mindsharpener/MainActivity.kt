package com.example.mindsharpener

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mindsharpener.ui.theme.MindSharpenerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var userAnswer: EditText = findViewById(R.id.answer)
        var checkButton: Button = findViewById(R.id.checkButton)
        var userpoint: TextView = findViewById(R.id.marks)
        var level: RadioGroup = findViewById(R.id.levelGroup)
        var num1: TextView = findViewById(R.id.operand1)
        var num2: TextView = findViewById(R.id.operand2)
        var operator: TextView = findViewById(R.id.operator)


        level.setOnCheckedChangeListener {
            buttonView, isChecked -> val lv = when (level.checkedRadioButtonId) {
            R.id.lvl1 -> 1
            R.id.lvl2 -> 2
            R.id.lvl3 -> 3
            else -> 0
        }
            fun getRandomNum(level: Int): Int {
                var random = 0
                if (level == 1) {   //i3
                    random = (0..9).shuffled().last()  //generate 1 digit
                }
                else if (level == 2) {  //i5
                    random = (0..99).shuffled().last()  //generate 2 digits
                }
                else if (level == 3) {   //i7
                    random = (0..999).shuffled().last()  //generate 3 digits
                }
                else {
                    random = 0
                }
                return random
            }

            num1.setText(getRandomNum(lv).toString())  //setting first random value
            num2.setText(getRandomNum(lv).toString())  //set second random value

            var randomOperator = (0..3).shuffled().last() //generating random operator

            if (randomOperator == 0) {
                operator.setText("+")
            } else if (randomOperator == 1) {
                operator.setText("-")
            } else if (randomOperator == 2) {
                operator.setText("*")
            } else{
                operator.setText("/")
            }
        }

        fun checkAnswer(operator: String, userAnswer: Int, num1: Int, num2: Int): Boolean {
            var answer = 0
            if (operator == "+"){
                answer = num1 + num2
            }
            else if (operator == "-"){
                answer = num1 - num2
            }
            else if (operator == "*"){
                answer = num1 * num2
            }
            else if (operator == "/"){
                answer = num1 / num2
            }
            if(userAnswer == answer) {
                return true
            }
            else {
                return false
            }
        }

        var currentPoint = 0

        checkButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                    if (checkAnswer(
                            operator.text.toString(),
                            userAnswer.text.toString().toInt(),
                            num1.text.toString().toInt(),
                            num2.text.toString().toInt()
                        )
                    ) {
                        currentPoint++   //increase points
                    } else {
                        currentPoint--   //reduce points
                    }
                    userpoint.setText(currentPoint)
                }


        })

    }
}