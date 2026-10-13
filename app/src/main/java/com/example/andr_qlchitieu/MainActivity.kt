package com.example.andr_qlchitieu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Khai báo các biến lưu trữ thông tin khoản chi vừa thêm
    private var lastTenKC: String? = null
    private var lastSoTien: Double? = null
    private var lastGhiChu: String? = null

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
                        "Số tiền phải lớn hơn 0 và không được bỏ trống",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                // LƯU DỮ LIỆU VÀO BIẾN TOÀN CỤC TRƯỚC KHIN CLEAR GIAO DIỆN
                lastTenKC = tenkc
                lastSoTien = sotien
                lastGhiChu = ghichu

                // Thông báo và dọn sạch giao diện để nhập khoản khác
                Toast.makeText(this@MainActivity, "Thêm khoản chi thành công!", Toast.LENGTH_SHORT).show()
                edtTenKC.text?.clear()
                edtGhichu.text?.clear()
                edtTien.text?.clear()
            }
        }

        // 2. Xử lý Nút Xem chi tiết
        binding.btnDetail.setOnClickListener {
            // Kiểm tra xem người dùng đã bấm Thêm khoản chi nào chưa
            if (lastTenKC == null || lastSoTien == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Chưa có khoản chi nào! Vui lòng bấm Thêm trước.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Lấy dữ liệu từ biến toàn cục đã lưu để chuyển sang DetailActivity
            val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("key_NameKC", lastTenKC)
                putExtra("key_Money", lastSoTien)
                putExtra("key_Infor", lastGhiChu)
            }
            startActivity(intent)
        }
    }
}