package com.example.simplemvvm

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.simplemvvm.presentation.MainFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance())
            .commit()
    }
}