package dev.lcx06.t1_pm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Activity4 : AppCompatActivity() {

    private var originalColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        this.addBtnListeners()
        this.addChangeListener()

        this.originalColor = getColor(R.color.purple_700)
        this.addFocusListener()
    }

    private fun addBtnListeners() {
        val operate: (Boolean) -> Unit = { plus ->
            val view = findViewById<TextView>(R.id.numValue)

            var num = view.text.toString().toIntOrNull()
            if (num == null) {
                Toast.makeText(this, "Debes introducir un número valido primero", Toast.LENGTH_SHORT).show()
            } else {
                num += if (plus) 1 else -1
                if (num < 0) num = 0
                view.text = num.toString()
            }

            findViewById<EditText>(R.id.numField).clearFocus()
        }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { operate(false) }
        findViewById<Button>(R.id.btnPlus).setOnClickListener { operate(true) }
    }

    private fun addFocusListener() {
        findViewById<EditText>(R.id.numField).setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.teal_700, theme) else originalColor

            findViewById<Button>(R.id.btnMinus).setBackgroundColor(color)
            findViewById<Button>(R.id.btnPlus).setBackgroundColor(color)
        }
    }

    private fun addChangeListener() {
        val field = findViewById<EditText>(R.id.numField)
        field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val view = findViewById<TextView>(R.id.numValue)
                if (s.toString().isEmpty()) {
                    Toast.makeText(this@Activity4, "El número no puede estar vacio", Toast.LENGTH_SHORT).show()
                    s?.append(view.text)
                }
                val num = s.toString().toIntOrNull()
                if (num != null && num >= 0) view.text = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}