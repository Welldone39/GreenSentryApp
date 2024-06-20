package com.wildan.greensentry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.wildan.greensentry.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val imageUriString = intent.getStringExtra(EXTRA_IMAGE)
        val prediction = intent.getStringExtra(EXTRA_PREDICTION)

        val imageUri = Uri.parse(imageUriString)

        binding.resultImage.setImageURI(imageUri)
        binding.resultText.text = prediction

        binding.btnClassification.setOnClickListener {
            navigateToClassificationActivity()
        }

        binding.BtnHome.setOnClickListener {
            navigateToMainActivity()
        }
    }
    private fun navigateToClassificationActivity() {
        val intent = Intent(this, ClassificationActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_PREDICTION = "extra_prediction"
    }
}
