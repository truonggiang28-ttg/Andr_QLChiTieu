package com.example.andr_qlchitieu

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity() {
    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if (result.resultCode == RESULT_OK){
            result.data?.extras?.let {
                bundle ->
                with(binding){
                    tvNameKC.text = bundle.getString("key_TenMoi")
                    tvMoney.text = bundle.getDouble("key_TienMoi").toString()
                    tvGhiChu.text = bundle.getString("key_GhiChuMoi")
                }
            }
        }

    }
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

        binding.btnEdit.setOnClickListener {
            with(binding){
                val intent = Intent(this@DetailActivity, ChinhSuaChiTietActivity::class.java).apply{
                    putExtra("key_edtName", tvNameKC.text)
                    putExtra("key_edtTien",tvMoney.text)
                    putExtra("key_edtGhiChu",tvGhiChu.text)
                }
                editLauncher
            }
        }
    }
}