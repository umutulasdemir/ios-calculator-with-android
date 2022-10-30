package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var lastResultString = ""
    var opPressed = false // checks if operator clicked twice in a row without result button.
    var lastResultFloat = 0.0F
    var operatorType: Int = 6 // 0:No operator, 1: Add, 2: Subtract, 3: Multiply, 4: Divide 5: Result, 6:No operator(first extract)
    var opKey = 0
    var errorCheck = false
    var countOnce = true
    var newNumber = false

    lateinit var resultTextView: TextView
    lateinit var pressOne: androidx.appcompat.widget.AppCompatButton
    lateinit var pressTwo: androidx.appcompat.widget.AppCompatButton
    lateinit var pressThree: androidx.appcompat.widget.AppCompatButton
    lateinit var pressFour: androidx.appcompat.widget.AppCompatButton
    lateinit var pressFive: androidx.appcompat.widget.AppCompatButton
    lateinit var pressSix: androidx.appcompat.widget.AppCompatButton
    lateinit var pressSeven: androidx.appcompat.widget.AppCompatButton
    lateinit var pressEight: androidx.appcompat.widget.AppCompatButton
    lateinit var pressNine: androidx.appcompat.widget.AppCompatButton
    lateinit var pressZero: androidx.appcompat.widget.AppCompatButton
    lateinit var pressDivider: androidx.appcompat.widget.AppCompatButton
    lateinit var pressMultiplier: androidx.appcompat.widget.AppCompatButton
    lateinit var pressSubtractor: androidx.appcompat.widget.AppCompatButton
    lateinit var pressCleaner: androidx.appcompat.widget.AppCompatButton
    lateinit var pressAdder: androidx.appcompat.widget.AppCompatButton
    lateinit var pressResultButton: androidx.appcompat.widget.AppCompatButton
    lateinit var pressCommaButton: androidx.appcompat.widget.AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        define()
        action()
    }
    fun define(){
        resultTextView = findViewById(R.id.resultTextView)
        pressOne = findViewById(R.id.oneButton)
        pressTwo = findViewById(R.id.twoButton)
        pressThree = findViewById(R.id.threeButton)
        pressFour = findViewById(R.id.fourButton)
        pressFive = findViewById(R.id.fiveButton)
        pressSix = findViewById(R.id.sixButton)
        pressSeven = findViewById(R.id.sevenButton)
        pressEight = findViewById(R.id.eightButton)
        pressNine = findViewById(R.id.nineButton)
        pressZero = findViewById(R.id.zeroButton)
        pressDivider = findViewById(R.id.divideButton)
        pressMultiplier = findViewById(R.id.multiplyButton)
        pressSubtractor = findViewById(R.id.subtractButton)
        pressAdder = findViewById(R.id.addButton)
        pressCleaner = findViewById(R.id.clearButton)
        pressResultButton = findViewById(R.id.resultButton)
        pressCommaButton = findViewById(R.id.commaButton)
    }

    fun action(){
        pressAdder.setOnClickListener {
            if (opPressed&&opKey==0) doTheMath()
            if (operatorType!=1){
                operatorType = 1
                lastResultFloat = resultTextView.text.toString().toFloat()
                opPressed = true
            }
            opKey = 1
            newNumber = true
        }
        pressSubtractor.setOnClickListener {
            if (opPressed&&opKey==0) doTheMath()
            if (operatorType!=2){
                operatorType = 2
                lastResultFloat = resultTextView.text.toString().toFloat()
                opPressed = true
            }
            opKey = 2
            newNumber = true
        }
        pressMultiplier.setOnClickListener {
            if (opPressed&&opKey==0) doTheMath()
            if (operatorType!=3){
                operatorType = 3
                lastResultFloat = resultTextView.text.toString().toFloat()
                opPressed = true
            }
            opKey = 3
            newNumber = true
        }
        pressDivider.setOnClickListener {
            if (opPressed&&opKey==0) doTheMath()
            if (operatorType!=4){
                operatorType = 4
                lastResultFloat = resultTextView.text.toString().toFloat()
                opPressed = true
            }
            opKey = 4
            newNumber = true
        }
        pressOne.setOnClickListener {
            opKey = 0
            processCheck("1")
        }
        pressTwo.setOnClickListener {
            opKey = 0
            processCheck("2")
        }
        pressThree.setOnClickListener {
            opKey = 0
            processCheck("3")
        }
        pressFour.setOnClickListener {
            opKey = 0
            processCheck("4")
        }
        pressFive.setOnClickListener {
            opKey = 0
            processCheck("5")
        }
        pressSix.setOnClickListener {
            opKey = 0
            processCheck("6")
        }
        pressSeven.setOnClickListener {
            opKey = 0
            processCheck("7")
        }
        pressEight.setOnClickListener {
            opKey = 0
            processCheck("8")
        }
        pressNine.setOnClickListener {
            opKey = 0
            processCheck("9")
        }
        pressZero.setOnClickListener {
            opKey = 0
            processCheck("0")
        }
        pressCommaButton.setOnClickListener {
            processCheck(",")
        }
        pressResultButton.setOnClickListener {
            if (!resultTextView.text.equals("")) doTheMath()
            opKey = 0
        }
        pressCleaner.setOnClickListener {
            clear()
        }
    }

    fun clear(){
        lastResultFloat = 0.0F
        lastResultString = ""
        resultTextView.text = "0"
        operatorType = 6
        opPressed = false
        opKey = 0
    }

    fun processCheck(item: String){
        Log.i("LRF ZRF: ", "kSS"+operatorType)
        when (operatorType) {
            0 -> resultTextView.text = resultTextView.text.toString().plus(item)
            6 -> {
                resultTextView.text = item
                operatorType = 0

            }
            else -> {
                if (newNumber){
                    resultTextView.text = item
                    newNumber = false
                }
                else resultTextView.text = resultTextView.text.toString().plus(item)
            }

        }
    }

    fun doTheMath(){
        Log.i("LRF: ",""+lastResultFloat)
        Log.i("LRF RTV: ", resultTextView.text.toString() + " opType: " + operatorType)
        when (operatorType) {
            1 -> lastResultFloat += resultTextView.text.toString().toFloat()
            2 -> lastResultFloat -= resultTextView.text.toString().toFloat()
            3 -> lastResultFloat *= resultTextView.text.toString().toFloat()
            4 -> try {
                lastResultFloat /= resultTextView.text.toString().toFloat()
            }catch (e: java.lang.ArithmeticException){
                errorCheck = true
            }
        }
        if (!errorCheck) resultTextView.text = lastResultFloat.toString()
        else resultTextView.text = "Error"
        operatorType = 6
        opPressed = false
        Log.i("EE2",""+opPressed + operatorType)
        countOnce=false
    }


}