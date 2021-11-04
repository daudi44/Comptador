package me.danielaudibielsa.dam.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.util.Log.*
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val INITIAL_TIME = 15

    private val TAG = MainActivity::class.java.simpleName

    internal lateinit var tapMeButton: Button
    internal lateinit var timerTextView: TextView
    internal lateinit var counterTextView: TextView
    internal var counter = 0;
    internal var time = INITIAL_TIME;

    internal var appStarted = false;
    internal lateinit var countdownTimer : CountDownTimer;
    internal val initialCountDownTimer : Long = time.toLong() * 1000;
    internal val intervalCountDownTimer : Long = 1000;

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "HELLO WORLD! onCreate")
        Log.d(TAG, counter.toString())
        Log.d(TAG, time.toString())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timerTextView = findViewById(R.id.timer)
        counterTextView = findViewById(R.id.counter)

        tapMeButton.setOnClickListener{
            if(!appStarted){
                startGame()
            }
            incrementCounter()
        }

        timerTextView.text = getString(R.string.timerText, time);


    }

    private fun initCountdown(){
        countdownTimer = object : CountDownTimer(initialCountDownTimer, intervalCountDownTimer){
            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                timerTextView.text = getString(R.string.timerText, timeLeft);
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

    private fun incrementCounter(){
        counter = counter + 1;
        counterTextView.text = counter.toString();
    }


    //Iniciar la partida
    private fun startGame() {
        countdownTimer.start()
        appStarted = true
    }

    private fun endGame(){
        Toast.makeText(this, getString(R.string.endGame), Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun resetGame() {
        Toast.makeText(this, getString(R.string.puntFinal) + ' ' + counter, Toast.LENGTH_LONG).show()
        counter = 0;
        time = INITIAL_TIME
        timerTextView.text = getString(R.string.timerText, INITIAL_TIME);
        appStarted = false;
        counterTextView.text = counter.toString()
        initCountdown()
    }
}