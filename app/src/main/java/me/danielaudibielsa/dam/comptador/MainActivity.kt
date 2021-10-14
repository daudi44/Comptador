package me.danielaudibielsa.dam.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var timerTextView: TextView
    internal lateinit var counterTextView: TextView
    internal var counter = 0;
    internal var time = 60;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        timerTextView = findViewById(R.id.timer)
        counterTextView = findViewById(R.id.counter)

        //Definir valor inicial del counter

        //TODO

        tapMeButton.setOnClickListener{
            incrementCounter()
        }

//        timerTextView.text = time.toString();
        timerTextView.text = getString(R.string.timerText, time);
    }

    private fun incrementCounter(){
        counter = counter + 1;
        counterTextView.text = counter.toString();
    }
}