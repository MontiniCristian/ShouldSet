package com.cristian.shouldemo


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristian.shouldemo.fragment.FragmentMain

class MainActivity : AppCompatActivity() {

    val mainFragmentInstance = FragmentMain()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = applicationContext.getColor(R.color.colorPrimary)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, mainFragmentInstance)
            .commit()

    }

}
