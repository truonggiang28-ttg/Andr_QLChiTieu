package com.example.andr_qlchitieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.andr_qlchitieu.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var isDaThanhToan: Boolean = false

    // Câu 3: Activity Result API nhận kết quả sửa từ ChinhSuaChiTieuActivity
    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.extras?.let { bundle ->
                binding.tvTenKC.text = bundle.getString("key_NameKC")
                binding.tvTien.text = bundle.getDouble("key_Money", 0.0).toString()
                binding.tvInfor.text = bundle.getString("key_Infor")
                isDaThanhToan = bundle.getBoolean("key_Status", false)
                binding.tvStatus.text = if (isDaThanhToan) "Đã thanh toán" else "Chưa thanh toán"

                Toast.makeText(this, "Đã cập nhật chỉnh sửa!", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Câu 3: Nếu hủy chỉnh sửa thì giữ nguyên
            Toast.makeText(this, "Đã hủy chỉnh sửa", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Nguyễn Tấn Chính - MSSV - Quản lý chi tiêu cá nhân"

        // Nhận dữ liệu từ MainActivity gửi sang
        intent.extras?.let { bundle ->
            with(binding) {
                val namekc = bundle.getString("key_NameKC")
                val tienkc = bundle.getDouble("key_Money", 0.0)
                val ghichukc = bundle.getString("key_Infor")
                isDaThanhToan = bundle.getBoolean("key_Status", false)

                tvTenKC.text = "${namekc}"
                tvTien.text = "${tienkc}"
                tvInfor.text = "${ghichukc}"
                tvStatus.text = if (isDaThanhToan) "Đã thanh toán" else "Chưa thanh toán"
            }
        }

        // Câu 3: Bấm Sửa -> Truyền dữ liệu hiện tại sang ChinhSuaChiTieuActivity
        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, ChinhSuaChiTieuActivity::class.java).apply {
                putExtra("key_NameKC", binding.tvTenKC.text.toString())
                putExtra("key_Money", binding.tvTien.text.toString().toDoubleOrNull() ?: 0.0)
                putExtra("key_Infor", binding.tvInfor.text.toString())
                putExtra("key_Status", isDaThanhToan)
            }
            editLauncher.launch(intent)
        }

        // Quay lại MainActivity
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}