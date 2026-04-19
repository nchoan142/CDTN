package com.conghoan.sinhviencntt.network;

import com.conghoan.sinhviencntt.model.*;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    // Auth
    @POST("api/auth/login")
    Call<ApiResponse<LoginResponse>> login(@Body Map<String, String> body);

    @POST("api/auth/register")
    Call<ApiResponse<String>> register(@Body Map<String, String> body);

    @POST("api/auth/tao-tai-khoan")
    Call<ApiResponse<String>> taoTaiKhoan(@Body Map<String, String> body);

    @POST("api/auth/change-password")
    Call<ApiResponse<String>> changePassword(@Body Map<String, String> body);

    // Sinh vien
    @GET("api/v1/sinhvien/{msv}")
    Call<Map<String, Object>> getSinhVien(@Path("msv") String msv);

    @GET("api/v1/sinhvien/count")
    Call<Map<String, Object>> getSinhVienCount();

    // Bang diem
    @GET("api/v1/bangdiem/{msv}")
    Call<List<BangDiemModel>> getBangDiem(@Path("msv") String msv);

    // Thoi khoa bieu
    @GET("api/v1/tkb/{msv}")
    Call<List<ThoiKhoaBieuModel>> getTKB(@Path("msv") String msv);

    // Giang vien
    @GET("api/v1/giangvien")
    Call<List<GiangVienModel>> getGiangVien();

    // Bieu mau
    @GET("api/v1/bieumau")
    Call<List<BieuMauModel>> getBieuMau();

    // Thong bao
    @GET("api/v1/thongbao")
    Call<List<ThongBaoModel>> getThongBao();

    // Hoi dap
    @GET("api/v1/hoidap")
    Call<List<HoiDapModel>> getHoiDap();

    @POST("api/v1/hoidap")
    Call<ApiResponse<String>> guiCauHoi(@Body Map<String, String> body);
}
