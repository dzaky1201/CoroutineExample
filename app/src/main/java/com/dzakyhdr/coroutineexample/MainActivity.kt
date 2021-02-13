package com.dzakyhdr.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dzakyhdr.coroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            binding.txtNumber.text = count++.toString()
        }

        binding.btnDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadDataUser()
            }

        }
    }

    private suspend fun downloadDataUser() {
        for (i in 1..200000){
            withContext(Dispatchers.Main){
                binding.txtShowMain.text = "Downloading User $i ${Thread.currentThread().name}"
            }
        }
    }
}