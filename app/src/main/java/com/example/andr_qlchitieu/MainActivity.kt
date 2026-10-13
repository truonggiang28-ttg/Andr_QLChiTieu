package com.example.andr_qlchitieu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Xử lý Nút Thêm
        binding.btnThem.setOnClickListener {
            with(binding) {
                val tenkc = edtTenKC.text.toString().trim()
                val ghichu = edtGhichu.text.toString().trim()
                val tien = edtTien.text.toString().trim()

                val sotien = tien.toDoubleOrNull()
                if (sotien == null || sotien <= 0) {
                    Toast.makeText(
                        this@MainActivity,
                        "Tiền phải lớn hơn 0 hoặc không được bỏ trống",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                // Thông báo thêm thành công và dọn sạch ô nhập liệu
                Toast.makeText(this@MainActivity, "Thêm thành công!", Toast.LENGTH_SHORT).show()
                edtTenKC.text?.clear()
                edtGhichu.text?.clear()
                edtTien.text?.clear()
            }
        }

        // 2. Xử lý Nút Xem chi tiết
        binding.btnDetail.setOnClickListener {
            with(binding) {
                val tenkc = edtTenKC.text.toString().trim()
                val ghichu = edtGhichu.text.toString().trim()
                val tien = edtTien.text.toString().trim()

                val sotien = tien.toDoubleOrNull()
                if (sotien == null || sotien <= 0) {
                    Toast.makeText(
                        this@MainActivity,
                        "Vui lòng nhập số tiền hợp lệ trước khi xem chi tiết",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                // Khởi tạo Intent đúng cú pháp và ghép đúng dữ liệu
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("key_NameKC", tenkc)
                    putExtra("key_Money", sotien) // Khai báo đúng key_Money với số tiền
                    putExtra("key_Infor", ghichu) // Khai báo đúng key_Infor với ghi chú
                }
                startActivity(intent)
            }
        }
    }
}