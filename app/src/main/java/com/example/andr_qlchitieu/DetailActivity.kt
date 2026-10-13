package com.example.andr_qlchitieu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let{
            bundle ->
            with(binding){
                val namekc = bundle.getString("key_NameKC")
                val tienkc = bundle.getDouble("key_Money", 0.0)
                val ghichukc = bundle.getString("key_Infor")

                tvTenKC.text = "${namekc}"
                tvTien.text = "${tienkc}"
                tvInfor.text = "${ghichukc}"
            }
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}