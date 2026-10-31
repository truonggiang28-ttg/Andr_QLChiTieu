package com.example.andr_qlchitieu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let{
            bundle ->
            with(binding){
                tvNameKC.text = bundle.getString("key_NameKC")
                tvMoney.text = bundle.getDouble("key_SoTien").toString()
                tvGhiChu.text = bundle.getString("key_GhiChu")
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}