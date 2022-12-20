package dev.lcx06.t1_pm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class Activity6 : AppCompatActivity() {
    private var started = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = intent.getIntExtra("theme", R.style.Theme_T1PM)
        setTheme(theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)

        started = intent.getBooleanExtra("started", false)

        val input = findViewById<EditText>(R.id.act5_input)
        input.alpha = if (started) 1f else 0f
        input.setText(intent.getStringExtra("content"))
        if (started) input.requestFocus()

        findViewById<Button>(R.id.act5_btn).text = if (started) "Finalizar" else "Comenzar"
        findViewById<ImageView>(R.id.act5_image).alpha = if (intent.getStringExtra("content")?.contains("Wayne") == true) 1f else 0f

        this.handleClickEvents()
        this.handleTextEdit()
        this.handleFocusEvents()
    }

    private fun handleClickEvents() {
        val btn = findViewById<Button>(R.id.act5_btn)
        btn.setOnClickListener {
            started = !started
            btn.text = if (started) "Finalizar" else "Comenzar"

            val input = findViewById<EditText>(R.id.act5_input)
            input.alpha = if (started) 1f else 0f
            input.clearFocus()
        }
    }

    private fun handleTextEdit() {
        findViewById<EditText>(R.id.act5_input).addTextChangedListener(
            afterTextChanged = {
                val value = it.toString()
                findViewById<ImageView>(R.id.act5_image).alpha = if (value.contains("Wayne")) 1f else 0f
                val textTheme = if (value.contains("Joker")) R.style.Theme_Joker else R.style.Theme_T1PM
                if (textTheme != intent.getIntExtra("theme", R.style.Theme_T1PM)) {
                    val intent = Intent(this, Activity6::class.java)
                    intent.putExtra("theme", textTheme)
                    intent.putExtra("content", value)
                    intent.putExtra("started", started)
                    startActivity(intent)
                }
            }
        )
    }

    private fun handleFocusEvents() {
        val input = findViewById<EditText>(R.id.act5_input)
        input.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus && input.text.toString() == "Introduce tu nombre")
                input.setText("")
            else if (!hasFocus && input.text.toString() == "")
                input.setText("Introduce tu nombre")
        }
    }
}