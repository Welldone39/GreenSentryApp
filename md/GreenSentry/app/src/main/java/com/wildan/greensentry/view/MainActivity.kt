package com.wildan.greensentry

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wildan.greensentry.databinding.ActivityMainBinding
import com.wildan.greensentry.view.LoginActivity
import com.wildan.greensentry.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.aboutButton.setOnClickListener(this)
        binding.classificationBtn.setOnClickListener(this)

        playAnimation()

        newsAdapter = NewsAdapter(emptyList())
        val recyclerView = findViewById<RecyclerView>(R.id.newsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter

        val token = SessionManager.getToken(this)
        token?.let {
            mainViewModel.fetchNews(it).observe(this) { newsList ->
                newsAdapter.updateData(newsList)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.language -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            R.id.Donasi -> {
                val donasiIntent = Intent(this, DonasiActivity::class.java)
                startActivity(donasiIntent)
                true
            }
            R.id.Logout -> {
                SessionManager.clearData(this)
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.aboutButton -> {
                val aboutAct = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutAct)
            }
            R.id.classificationBtn -> {
                val classification = Intent(this@MainActivity, ClassificationActivity::class.java)
                startActivity(classification)
            }
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.previewImageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        ObjectAnimator.ofFloat(binding.aboutButton, View.ALPHA, 0f, 1f).apply {
            duration = 1000
        }.start()

        ObjectAnimator.ofFloat(binding.classificationBtn, View.ALPHA, 0f, 1f).apply {
            duration = 1000
        }.start()
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        showExitConfirmationDialog()
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Keluar")
        builder.setMessage("Anda yakin ingin keluar dari aplikasi?")
        builder.setPositiveButton("Ya") { dialog, which ->
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
        builder.setNegativeButton("Tidak") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
