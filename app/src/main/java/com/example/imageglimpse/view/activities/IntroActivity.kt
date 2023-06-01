package com.example.imageglimpse.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imageglimpse.R
import com.example.imageglimpse.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var introBinding:ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introBinding= ActivityIntroBinding.inflate(layoutInflater)
        setContentView(introBinding.root)

        val toolbar = introBinding.introToolbar
        setSupportActionBar(toolbar)

        toolbar.title = resources.getString(R.string.app_name)

        val anim= introBinding.lottieAnimation
        anim.playAnimation()

        val intent =Intent(this, MainActivity::class.java)

        introBinding.btnViewImages.setOnClickListener {
          startActivity(intent)
        }

    }
}