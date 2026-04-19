package com.conghoan.sinhviencntt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.conghoan.sinhviencntt.model.ApiResponse;
import com.conghoan.sinhviencntt.network.ApiClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.conghoan.sinhviencntt.R;
import com.conghoan.sinhviencntt.adapter.DashboardAdapter;
import com.conghoan.sinhviencntt.model.DashboardItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvDashboard;
    private TextView tvStudentName, tvStudentMsv;
    private ImageView btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadUserInfo();
        setupDashboard();
        setupLogout();
        setupChangePassword();
    }

    private void initViews() {
        rvDashboard = findViewById(R.id.rv_dashboard);
        tvStudentName = findViewById(R.id.tv_student_name);
        tvStudentMsv = findViewById(R.id.tv_student_msv);
        btnLogout = findViewById(R.id.btn_logout);
    }

    private void loadUserInfo() {
        SharedPreferences prefs = getSharedPreferences("SinhVienCNTT", MODE_PRIVATE);
        String msv = prefs.getString("msv", "");
        String name = prefs.getString("student_name", "Sinh viên");

        tvStudentName.setText(name);
        tvStudentMsv.setText("MSV: " + msv);
    }

    private void setupDashboard() {
        List<DashboardItem> items = new ArrayList<>();

        items.add(new DashboardItem(
                getString(R.string.menu_profile),
                "Xem hồ sơ & thông tin cá nhân",
                R.drawable.ic_profile,
                R.color.card_profile,
                R.color.card_profile_icon,
                ThongTinCaNhanActivity.class));

        items.add(new DashboardItem(
                getString(R.string.menu_tkb),
                "Xem lịch học, lịch thi theo tuần",
                R.drawable.ic_schedule,
                R.color.card_tkb,
                R.color.card_tkb_icon,
                TKBActivity.class));

        items.add(new DashboardItem(
                getString(R.string.menu_bangdiem),
                "Tra cứu điểm các môn theo kỳ",
                R.drawable.ic_grade,
                R.color.card_bangdiem,
                R.color.card_bangdiem_icon,
                BangDiemActivity.class));

        items.add(new DashboardItem(
                getString(R.string.menu_khoa),
                "Thông tin khoa & giảng viên",
                R.drawable.ic_faculty,
                R.color.card_khoa,
                R.color.card_khoa_icon,
                KhoaCNTTActivity.class));

        items.add(new DashboardItem(
                getString(R.string.menu_phongdaotao),
                "Biểu mẫu, thủ tục hành chính",
                R.drawable.ic_training,
                R.color.card_phongdaotao,
                R.color.card_phongdaotao_icon,
                PhongDaoTaoActivity.class));

        items.add(new DashboardItem(
                getString(R.string.menu_truong),
                "Tin tức & sự kiện trường",
                R.drawable.ic_university,
                R.color.card_truong,
                R.color.card_truong_icon,
                TruongDHTLActivity.class));

        items.add(new DashboardItem(
                getString(R.string.menu_thongtin),
                "Học bổng, CLB, ngoại khoá",
                R.drawable.ic_info,
                R.color.card_thongtin,
                R.color.card_thongtin_icon,
                ThongTinKhacActivity.class));

        items.add(new DashboardItem(
                getString(R.string.menu_hoidap),
                "Đặt câu hỏi & nhận phản hồi",
                R.drawable.ic_qa,
                R.color.card_hoidap,
                R.color.card_hoidap_icon,
                HoiDapActivity.class));

        // Setup RecyclerView with 2-column grid
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvDashboard.setLayoutManager(layoutManager);
        rvDashboard.setAdapter(new DashboardAdapter(this, items));
        rvDashboard.setHasFixedSize(true);
    }

    private void setupLogout() {
        btnLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Đăng xuất")
                    .setMessage("Bạn có chắc muốn đăng xuất?")
                    .setPositiveButton("Đăng xuất", (dialog, which) -> {
                        SharedPreferences prefs = getSharedPreferences("SinhVienCNTT", MODE_PRIVATE);
                        prefs.edit()
                                .putBoolean("is_logged_in", false)
                                .remove("msv")
                                .remove("student_name")
                                .remove("token")
                                .remove("lop")
                                .remove("nganh")
                                .remove("khoa")
                                .apply();

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, 0);
                        finish();
                    })
                    .setNegativeButton("Huỷ", null)
                    .show();
        });
    }

    private void setupChangePassword() {
        ImageView btnChangePass = findViewById(R.id.btn_change_password);
        btnChangePass.setOnClickListener(v -> {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 20, 50, 0);

            EditText edtNewPass = new EditText(this);
            edtNewPass.setHint("Mật khẩu mới");
            edtNewPass.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            layout.addView(edtNewPass);

            EditText edtConfirm = new EditText(this);
            edtConfirm.setHint("Xác nhận mật khẩu");
            edtConfirm.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            layout.addView(edtConfirm);

            new AlertDialog.Builder(this)
                    .setTitle("Đổi mật khẩu")
                    .setView(layout)
                    .setPositiveButton("Đổi", (dialog, which) -> {
                        String newPass = edtNewPass.getText().toString().trim();
                        String confirm = edtConfirm.getText().toString().trim();

                        if (newPass.isEmpty() || newPass.length() < 6) {
                            Toast.makeText(this, "Mật khẩu phải từ 6 ký tự", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!newPass.equals(confirm)) {
                            Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Map<String, String> body = new HashMap<>();
                        body.put("newPassword", newPass);

                        ApiClient.getApiService(this).changePassword(body).enqueue(new Callback<ApiResponse<String>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                                if (response.isSuccessful() && response.body() != null && response.body().getCode() == 0) {
                                    Toast.makeText(MainActivity.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                            }
                        });
                    })
                    .setNegativeButton("Huỷ", null)
                    .show();
        });
    }
}
