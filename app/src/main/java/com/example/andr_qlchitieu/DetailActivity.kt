package com.example.andr_qlchitieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.extras?.let { bundle ->
                binding.tvTenKC.text = bundle.getString("key_NameKC")
                binding.tvTien.text = bundle.getDouble("key_Money", 0.0).toString()
                binding.tvInfor.text = bundle.getString("key_Infor")
            }
        }
    }

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

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, ChinhSuaChiTieuActivity::class.java).apply {
                putExtra("key_NameKC", binding.tvTenKC.text.toString())
                putExtra("key_Money", binding.tvTien.text.toString().toDoubleOrNull() ?: 0.0)
                putExtra("key_Infor", binding.tvInfor.text.toString())
            }
            editLauncher.launch(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}