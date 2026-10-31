package com.example.andr_qlchitieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ChinhSuaChiTietActivityBinding

class ChinhSuaChiTietActivity : AppCompatActivity(){
    private lateinit var binding: ChinhSuaChiTietActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.let{
            bundle ->
            with(binding){
                val TenNow_313 = bundle.getString("key_edtName")
                val TienNow_313 = bundle.getDouble("key_edtTien")
                val GhiChuNow_313 = bundle.getString("key_edtGhiChu")

                edtNameNew313.setText(TenNow_313)
                edtTienNew313.setText(TienNow_313.toString())
                edtGhiChuNew313.setText(GhiChuNow_313)

            }

        }
        binding.btnLuu.setOnClickListener {
            with(binding){
                val TenMoi_313 = edtNameNew313.text.toString()
                val TienMoi_313 = edtTienNew313.text.toString()
                val GhiChuMoi_313 = edtGhiChuNew313.text.toString()


                val resultIntent = Intent().apply{
                    putExtra("key_TenMoi",TenMoi_313)
                    putExtra("key_TienMoi",TienMoi_313)
                    putExtra("key_GhiChuMoi",GhiChuMoi_313)
                }
                setResult(Activity.RESULT_OK,resultIntent)
            }
        }
        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}