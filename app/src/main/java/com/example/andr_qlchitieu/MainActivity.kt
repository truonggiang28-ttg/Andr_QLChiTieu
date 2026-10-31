package com.example.andr_qlchitieu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andr_qlchitieu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var TenKC_313 : String? = null
    private var SoTien_313 : Double? = null
    private var GhiChu_313 : String? = null
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnThem.setOnClickListener {
            with(binding){

                val edtTen_313 = edtNameKC.text.toString().trim()
                val edtTien_313 = edtMoney.text.toString().trim()
                val edtGhiChu_313 = edtGhiChu.text.toString().trim()

                val tienDouble_313  = edtTien_313.toDoubleOrNull()
                if ( edtTen_313.isEmpty() || edtTien_313.isEmpty() ||tienDouble_313 == null )
                {
                    Toast.makeText(this@MainActivity,
                        "Thong tin khong hop le",
                        Toast.LENGTH_SHORT).show()
                }

                TenKC_313 = edtTen_313
                SoTien_313 = tienDouble_313
                GhiChu_313 = edtGhiChu_313

                Toast.makeText(this@MainActivity, "Đã lưu thành công!", Toast.LENGTH_SHORT).show()

            }
        }

        binding.btnGui.setOnClickListener {
            with(binding){
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    .apply{
                        putExtra("key_NameKC", TenKC_313)
                        putExtra("key_SoTien", SoTien_313)
                        putExtra("key_GhiChu", GhiChu_313)
                    }
                startActivity(intent)
            }

        }

    }
}