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

class Activity5 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.addListenerToEditText()
        this.addListenerToButton()

        binding.button.isEnabled = false
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