package com.example.andr_qlchitieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityChinhSuaChiTieuBinding

class ChinhSuaChiTieuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChinhSuaChiTieuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChinhSuaChiTieuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Nhận dữ liệu hiện tại từ màn hình Chi tiết và hiển thị sẵn lên EditText
        val tenHienTai = intent.getStringExtra("key_NameKC") ?: ""
        val tienHienTai = intent.getDoubleExtra("key_Money", 0.0)
        val ghiChuHienTai = intent.getStringExtra("key_Infor") ?: ""

        with(binding) {
            edtEditTenKC.setText(tenHienTai)
            edtEditTien.setText(tienHienTai.toString())
            edtEditGhichu.setText(ghiChuHienTai)

            // 2. Nút Lưu: Trả dữ liệu mới về bằng setResult với RESULT_OK
            btnSave.setOnClickListener {
                val tenMoi = edtEditTenKC.text.toString().trim()
                val ghiChuMoi = edtEditGhichu.text.toString().trim()
                val tienStr = edtEditTien.text.toString().trim()
                val soTienMoi = tienStr.toDoubleOrNull()

                if (tenMoi.isEmpty()) {
                    Toast.makeText(this@ChinhSuaChiTieuActivity, "Tên không được để trống", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (soTienMoi == null || soTienMoi <= 0) {
                    Toast.makeText(this@ChinhSuaChiTieuActivity, "Số tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Đóng gói dữ liệu trả về
                val resultIntent = Intent().apply {
                    putExtra("key_NameKC", tenMoi)
                    putExtra("key_Money", soTienMoi)
                    putExtra("key_Infor", ghiChuMoi)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Đóng màn hình
            }

            // 3. Nút Hủy: Trả về RESULT_CANCELED (không đổi dữ liệu)
            btnCancel.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }
}