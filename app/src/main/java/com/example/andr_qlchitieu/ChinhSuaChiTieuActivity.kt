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

        supportActionBar?.title = "Nguyễn Tấn Chính - MSSV - Quản lý chi tiêu cá nhân"

        // 1. Hiển thị sẵn dữ liệu nhận từ màn hình Chi tiết
        with(binding) {
            edtEditTenKC.setText(intent.getStringExtra("key_NameKC"))
            edtEditTien.setText(intent.getDoubleExtra("key_Money", 0.0).toString())
            edtEditGhichu.setText(intent.getStringExtra("key_Infor"))
            cbEditDaThanhToan.isChecked = intent.getBooleanExtra("key_Status", false)

            // 2. Nút Lưu: Kiểm tra hợp lệ và gửi trả dữ liệu về qua setResult(RESULT_OK)
            btnSave.setOnClickListener {
                val ten = edtEditTenKC.text.toString().trim()
                val tien = edtEditTien.text.toString().toDoubleOrNull()
                val ghiChu = edtEditGhichu.text.toString().trim()
                val isPaid = cbEditDaThanhToan.isChecked

                if (ten.isBlank()) {
                    Toast.makeText(this@ChinhSuaChiTieuActivity, "Tên không được để trống!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (tien == null || tien <= 0) {
                    Toast.makeText(this@ChinhSuaChiTieuActivity, "Số tiền phải lớn hơn 0!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val returnIntent = Intent().apply {
                    putExtra("key_NameKC", ten)
                    putExtra("key_Money", tien)
                    putExtra("key_Infor", ghiChu)
                    putExtra("key_Status", isPaid)
                }
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }

            // 3. Nút Hủy: Trả về RESULT_CANCELED
            btnCancel.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }
}