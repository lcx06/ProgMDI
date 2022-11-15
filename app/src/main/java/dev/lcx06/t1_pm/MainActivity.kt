package dev.lcx06.t1_pm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import dev.lcx06.t1_pm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var onCreate = 0
    private var onStart = 1
    private var onResume = 2
    private var onPause = 3
    private var onRestart = 4
    private var onStop = 5
    private var onDestroy = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("onCreate", "$onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "$onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "$onResume")
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
}