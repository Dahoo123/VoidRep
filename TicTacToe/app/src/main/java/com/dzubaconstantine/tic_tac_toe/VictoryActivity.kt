package com.dzubaconstantine.tic_tac_toe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_victory.*

class VictoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_victory)

        val victoryChar = intent.getCharExtra("victoryChar", '\u0000')
        if (victoryChar == '?')
            victoryMessageTextView.text = "Ничья"
        else
            victoryMessageTextView.text = "Победил $victoryChar"
    }
}