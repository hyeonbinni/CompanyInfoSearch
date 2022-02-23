package com.hyeonbinni.companyinfosearch.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyeonbinni.companyinfosearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(ActivityMainBinding.inflate(layoutInflater)) {
            setContentView(root)
        }
    }

}