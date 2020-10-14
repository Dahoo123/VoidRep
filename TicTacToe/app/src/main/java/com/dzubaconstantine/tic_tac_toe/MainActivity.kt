package com.dzubaconstantine.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var turnCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startGameButtonClick(view: View) {
        val fieldSize = 3
        val players = "XO"

        view.isEnabled = false
        for (rowIndex in 0 until fieldSize) {
            val newRow = TableRow(this)
            for (buttonIndex in 0 until fieldSize) {
                val newButton = Button(this)
                newButton.id = fieldSize * rowIndex + buttonIndex
                newButton.setOnClickListener {
                    it.isEnabled = false
                    (it as Button).text = players[turnCount++ % players.length].toString()
                    val victoryChar = checkWinner()
                    if (victoryChar != '\u0000') {
                        val intent = Intent(this, VictoryActivity::class.java)
                        intent.putExtra("victoryChar", victoryChar)
                        startActivity(intent)
                        reset()
                    }
                }
                newRow.addView(newButton)
            }
            fieldTableLayout.addView(newRow)
        }
    }

    private fun reset() {
        for (rowIndex in 0 until fieldTableLayout.childCount) {
            for (buttonIndex in 0 until (fieldTableLayout.getChildAt(rowIndex) as TableRow).childCount) {
                ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(buttonIndex) as Button).isEnabled =
                    true
                ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(buttonIndex) as Button).text =
                    ""
            }
        }
    }

    // перепиши это быстрее...
    /** @return no winner - null char, draw - ?, else - winner char */
    private fun checkWinner(): Char {
        // row check
        for (rowIndex in 0 until fieldTableLayout.childCount)
            if (
                ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(0) as Button).text.isNotEmpty() &&
                ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(0) as Button).text ==
                ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(1) as Button).text &&
                ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(1) as Button).text ==
                ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(2) as Button).text
            )
                return ((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(0) as Button).text[0]

        // column check
        for (columnIndex in 0 until (fieldTableLayout.getChildAt(0) as TableRow).childCount) {
            if (
                ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(columnIndex) as Button).text.isNotEmpty() &&
                ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(columnIndex) as Button).text ==
                ((fieldTableLayout.getChildAt(1) as TableRow).getChildAt(columnIndex) as Button).text &&
                ((fieldTableLayout.getChildAt(1) as TableRow).getChildAt(columnIndex) as Button).text ==
                ((fieldTableLayout.getChildAt(2) as TableRow).getChildAt(columnIndex) as Button).text
            ) {
                return ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(columnIndex) as Button).text[0]
            }
        }

        if (
            ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(0) as Button).text.isNotEmpty() &&
            ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(0) as Button).text ==
            ((fieldTableLayout.getChildAt(1) as TableRow).getChildAt(1) as Button).text &&
            ((fieldTableLayout.getChildAt(1) as TableRow).getChildAt(1) as Button).text ==
            ((fieldTableLayout.getChildAt(2) as TableRow).getChildAt(2) as Button).text
        )
            return ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(0) as Button).text[0]

        if (
            ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(2) as Button).text.isNotEmpty() &&
            ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(2) as Button).text ==
            ((fieldTableLayout.getChildAt(1) as TableRow).getChildAt(1) as Button).text &&
            ((fieldTableLayout.getChildAt(1) as TableRow).getChildAt(1) as Button).text ==
            ((fieldTableLayout.getChildAt(2) as TableRow).getChildAt(0) as Button).text
        )
            return ((fieldTableLayout.getChildAt(0) as TableRow).getChildAt(2) as Button).text[0]

        var isDraw = true
        for (rowIndex in 0 until fieldTableLayout.childCount) {
            val newRow = TableRow(this)
            for (buttonIndex in 0 until (fieldTableLayout.getChildAt(rowIndex) as TableRow).childCount) {
                if (((fieldTableLayout.getChildAt(rowIndex) as TableRow).getChildAt(buttonIndex) as Button).text.isEmpty())
                    isDraw = false
            }
        }
        if (isDraw)
            return '?'
        return '\u0000'
    }
}