package me.danielaudibielsa.dam.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var timerTextView: TextView
    internal lateinit var counterTextView: TextView
    internal var counter = 0;
    internal var time = 60;

    internal var appStarted = false;
    internal lateinit var countdownTimer : CountDownTimer;
    internal val initialCountDownTimer : Long = 60000;
    internal val intervalCountDownTimer : Long = 1000;

    override fun onCreate(savedInstanceState: Bundle?) {
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
        timerTextView.text = getString(R.string.timerText, time);
        appStarted = false;
        counterTextView.text = counter.toString()
        initCountdown()
    }
}