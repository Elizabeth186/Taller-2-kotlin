package com.example.taller2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.taller2.databinding.ActivityMainBinding
import com.example.taller2.repository.ProductRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()
    }
    private fun buildList() {

        val repository = ProductRepository.getRepository(this)

        val layoutManager = GridLayoutManager(this, 1)

        lifecycleScope.launch {
            repository.allProduct.collect { products ->
                binding.rvVehicles.apply {
                    adapter = ProductAdapter(products, this@MainActivity)
                    setLayoutManager(layoutManager)
                }
            }
        }
    }
    private fun addListeners() {
        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }



    }
    

}