package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.addingFragment) == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.addingFragment, AddingFragment())
                .commit()
    }
}