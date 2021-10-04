package com.example.taller2

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.taller2.databinding.ActivityAddProductBinding
import com.example.taller2.entities.Product
import com.example.taller2.repository.ProductRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }
    private fun addListener() {
        val repository = ProductRepository.getRepository(this)
        binding.btnAdd.setOnClickListener {
            hideKeyboard()
            with(binding) {
                if (etName.text.isBlank() || etPrice.text.isBlank() || etCant.text.isBlank()) {
                    Snackbar.make(this.root, "Some fields are empty", Snackbar.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.insert(
                                Product(
                                    name = etName.text.toString(),
                                    price = etPrice.text.toString().toInt(),
                                    cant = etCant.text.toString().toInt()
                                )
                            )
                        }
                        onBackPressed()
                    }
                }
            }
        }
    }
    private fun hideKeyboard() {
        val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

}