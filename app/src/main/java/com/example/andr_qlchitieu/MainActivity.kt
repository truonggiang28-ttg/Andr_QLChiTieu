package com.example.andr_qlchitieu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityMainBinding
import com.example.andr_qlchitieu.models.KhoanChi

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Khai báo các biến lưu trữ thông tin khoản chi vừa thêm
    private var khoanChiHienTai: KhoanChi? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Xử lý Nút Thêm
        binding.btnThem.setOnClickListener {
            with(binding) {
                val tenkc = edtTenKC.text.toString().trim()
                val ghichu = edtGhichu.text.toString().trim()
                val tienStr = edtTien.text.toString().trim()
                val isPaid = cbDaThanhToan.isChecked

                // Câu 5: Kiểm tra tên không để trống hoặc chỉ chứa khoảng trắng
                if (tenkc.isBlank()) {
                    Toast.makeText(this@MainActivity, "Tên khoản chi không được để trống!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                // Câu 5: Kiểm tra số tiền hợp lệ > 0
                val sotien = tienStr.toDoubleOrNull()
                val kc = KhoanChi(
                    maKhoanChi = "KC" + System.currentTimeMillis().toString().takeLast(4),
                    tenKhoanChi = tenkc,
                    soTien = sotien ?: 0.0,
                    ghiChu = ghichu,
                    daThanhToan = isPaid
                )
                // Câu 4: Dùng hàm của lớp để kiểm tra
                if (!kc.laSoTienHopLe()) {
                    Toast.makeText(this@MainActivity, "Số tiền phải là số lớn hơn 0!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                // Lưu lại đối tượng
                lastKhoanChi = kcu

                // Thông báo và dọn sạch giao diện để nhập khoản khác
                Toast.makeText(this@MainActivity, "Thêm khoản chi thành công!", Toast.LENGTH_SHORT).show()
                edtTenKC.text?.clear()
                edtGhichu.text?.clear()
                edtTien.text?.clear()
                cbDaThanhToan.isChecked = false
            }
        }

        // 2. Xử lý Nút Xem chi tiết
        binding.btnDetail.setOnClickListener {
            if (lastKhoanChi == null) {
                Toast.makeText(this@MainActivity, "Chưa có khoản chi nào! Vui lòng bấm Thêm trước.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("key_NameKC", lastKhoanChi?.tenKhoanChi)
                putExtra("key_Money", lastKhoanChi?.soTien)
                putExtra("key_Infor", lastKhoanChi?.ghiChu)
                putExtra("key_Status", lastKhoanChi?.daThanhToan)
            }
            startActivity(intent)
        }
    }
}