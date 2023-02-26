package com.example.wordle

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var wordToGuess=FourLetterWordList.getRandomFourLetterWord()
    override fun onCreate(savedInstanceState: Bundle?) {

        var guessCounter=3
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var simpleEditText:EditText= findViewById<EditText>(R.id.et_simple);
        val realFourWordId=findViewById<TextView>(R.id.textView)
        val textView1=findViewById<TextView>(R.id.textView3)
        val  textView2=findViewById<TextView>(R.id.textView7)
        val textView3=findViewById<TextView>(R.id.textView11)
        val guessButton=findViewById<Button>(R.id.button)
        val resetButton=findViewById<Button>(R.id.button2)
        val textViewGuess3Result=findViewById<TextView>(R.id.textView13)
        val textViewGuess1Result=findViewById<TextView>(R.id.textView5)
        val textViewGuess2Result=findViewById<TextView>(R.id.textView9)


        resetButton.setOnClickListener {
            //This will help to hide the keyboard
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
            wordToGuess=FourLetterWordList.getRandomFourLetterWord()
            realFourWordId.text=wordToGuess
            realFourWordId.visibility=View.INVISIBLE
            guessCounter=3
            textView1.visibility=View.INVISIBLE
            textView1.text=""
            textView2.visibility=View.INVISIBLE
            textView2.text=""
            textView3.visibility=View.INVISIBLE
            textView3.text=""
            textViewGuess1Result.visibility=View.INVISIBLE
            textViewGuess1Result.text=""
            textViewGuess2Result.visibility=View.INVISIBLE
            textViewGuess2Result.text=""
            textViewGuess3Result.visibility=View.INVISIBLE
            textViewGuess3Result.text=""
            guessButton.setEnabled(true)
            resetButton.visibility=View.GONE


        }

        //Event listener for Guess Button
        guessButton.setOnClickListener {
            //This will help to hide the keyboard
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }

                var currentText=simpleEditText.getText().toString()
                if(guessCounter==1){
                    guessCounter--
                    if(checkGuess(currentText)=="OOOO"){
                        Toast.makeText(it.context, "Congratulations you guess was right!", Toast.LENGTH_SHORT).show()
                        guessButton.setEnabled(false)
                    }else{
                        guessButton.setEnabled(false)
                        Toast.makeText(it.context,"You've exceeded the limit.",Toast.LENGTH_SHORT).show()
                        resetButton.visibility=View.VISIBLE
                        realFourWordId.visibility=View.VISIBLE
                        textViewGuess3Result.text=checkGuess(currentText)
                        textViewGuess3Result.visibility=View.VISIBLE
                        textView3.text=currentText
                        textView3.visibility=View.VISIBLE
                    }
                }else{
                    if(checkGuess(currentText)=="OOOO"){
                        guessCounter--
                        if(guessCounter==2){
                            textView1.text=currentText
                            textViewGuess1Result.text=checkGuess(currentText)
                            textView1.visibility=View.VISIBLE
                            textViewGuess1Result.visibility=View.VISIBLE
                        }else if(guessCounter==1){
                            textView2.text=currentText
                            textViewGuess2Result.text=checkGuess(currentText)
                            textView2.visibility=View.VISIBLE
                            textViewGuess2Result.visibility=View.VISIBLE
                        }
                        Toast.makeText(it.context, "Congratulations you guess was right!", Toast.LENGTH_SHORT).show()
                        guessButton.setEnabled(false)
                        resetButton.visibility=View.VISIBLE
                    }else{
                        if(guessCounter==3){
                                textView1.text=currentText
                                textViewGuess1Result.text=checkGuess(currentText)
                                textView1.visibility=View.VISIBLE
                                textViewGuess1Result.visibility=View.VISIBLE
                                guessCounter--
                        }else if (guessCounter==2){
                            textView2.text=currentText
                            textViewGuess2Result.text=checkGuess(currentText)
                            textView2.visibility=View.VISIBLE
                            textViewGuess2Result.visibility=View.VISIBLE
                            guessCounter--
                        }

                    }
                }
            simpleEditText.text.clear()
        }
        realFourWordId.text=wordToGuess


    }

    //function to guess the word
    private fun checkGuess(guess: String) : String {

        var result = ""
        for (i in 0..3) {
            if (guess[i].uppercaseChar() == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i].uppercaseChar() in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}