package dev.lcx06.t1_pm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import dev.lcx06.t1_pm.databinding.ActivityMainBinding

class Activity3 : AppCompatActivity() {
    private var onCreate = 0
    private var onStart = 1
    private var onResume = 2
    private var onPause = 3
    private var onRestart = 4
    private var onStop = 5
    private var onDestroy = 6

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("onCreate", "$onCreate")
        this.addListenerToEditText()
        this.addListenerToTextView()
        this.addListenerToButton()

        binding.button.isEnabled = false
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "$onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "$onResume")
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "$onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "$onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "$onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "$onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val sharedPref = getPreferences(MODE_PRIVATE)

        with(sharedPref.edit()) {
            putString("F1", binding.editTextTextPersonName.text.toString())
            putString("F2", findViewById<EditText>(R.id.editTextTextPersonName).text.toString())
            commit()
        }

        Log.d("onSaveInstanceState", outState.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val sharedPref = getPreferences(MODE_PRIVATE)
        with(sharedPref) {
            binding.editTextTextPersonName.setText(getString("F1", ""))
            findViewById<EditText>(R.id.editTextTextPersonName).setText(getString("F2", ""))

            Log.d("onRestoreInstanceState", getString("F1", "") ?: "")
            Log.d("onRestoreInstanceState", getString("F2", "") ?: "")
        }

        Log.d("onRestoreInstanceState", savedInstanceState.toString())
    }

    private fun addListenerToEditText() {
        val changeListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("beforeTextChanged", s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("onTextChanged", s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("afterTextChanged", s.toString())
                // binding.editTextTextPersonName2.text = binding.editTextTextPersonName.text
                binding.button.isEnabled = binding.editTextTextPersonName.text.isNotEmpty()
                        && binding.editTextTextPersonName2.text.isNotEmpty()
            }
        }

        val focusChange: View.OnFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) binding.textView.text = v.tag.toString()
            else binding.textView.text = "N/A"
        }

        binding.editTextTextPersonName.addTextChangedListener(changeListener)
        binding.editTextTextPersonName2.addTextChangedListener(changeListener)

        binding.editTextTextPersonName.onFocusChangeListener = focusChange
        binding.editTextTextPersonName2.onFocusChangeListener = focusChange
    }

    private fun addListenerToTextView() {
        binding.textView.setOnLongClickListener {
            Toast.makeText(this, "Transformando texto a mayuscula", Toast.LENGTH_LONG).show()
            binding.editTextTextPersonName.setText(
                binding.editTextTextPersonName.text.toString().uppercase()
            )
            true
        }
    }

    private fun addListenerToButton() {
        binding.button.setOnClickListener {
            if (binding.editTextTextPersonName.text.isEmpty() || binding.editTextTextPersonName2.text.isEmpty()) {
                Toast.makeText(this, "Escribe algo primero", Toast.LENGTH_LONG).show()
            } else {
                binding.editTextTextPersonName.setText(String.format("%s%s", binding.editTextTextPersonName.text, binding.editTextTextPersonName2.text))
                binding.editTextTextPersonName2.setText("")
            }
        }
    }
}