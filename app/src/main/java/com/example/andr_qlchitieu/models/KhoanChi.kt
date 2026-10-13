package com.example.andr_qlchitieu.models

import java.io.Serializable

class KhoanChi(
    var maKhoanChi: String = "",
    var tenKhoanChi: String = "",
    var soTien: Double = 0.0,
    var ghiChu: String = "",
    var daThanhToan: Boolean = false
) : Serializable {

    // 1. Hàm layThongTin() trả về chuỗi mô tả khoản chi
    fun layThongTin(): String {
        val trangThai = if (daThanhToan) "Đã thanh toán" else "Chưa thanh toán"
        return "Mã: $maKhoanChi | Tên: $tenKhoanChi | Số tiền: $soTien | Ghi chú: $ghiChu | Trạng thái: $trangThai"
    }

    // 2. Hàm laSoTienHopLe() kiểm tra số tiền > 0
    fun laSoTienHopLe(): Boolean {
        return soTien > 0
    }
}