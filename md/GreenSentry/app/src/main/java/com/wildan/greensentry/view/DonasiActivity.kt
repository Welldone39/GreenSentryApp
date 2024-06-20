package com.wildan.greensentry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class DonasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donasi)

        val buttonDonate = findViewById<Button>(R.id.konfirmasi)
        buttonDonate.setOnClickListener{
            val url = "https://docs.google.com/forms/d/e/1FAIpQLSdYYmwQ0xJbSnf-vIkEClP1T5F90xJtOIfgPYCE2htgUP4dgA/viewform"
            val intent =Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // Buat intent ke MainActivity
                val intent = Intent(this@DonasiActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                finish() // Optional, untuk menutup Activity saat ini jika diperlukan
            }
        }


        onBackPressedDispatcher.addCallback(this, callback)
    }
}
