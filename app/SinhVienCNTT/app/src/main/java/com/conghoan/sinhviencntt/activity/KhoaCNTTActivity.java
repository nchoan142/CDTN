package com.conghoan.sinhviencntt.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.conghoan.sinhviencntt.R;
import com.conghoan.sinhviencntt.model.GiangVienModel;
import com.conghoan.sinhviencntt.network.ApiClient;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhoaCNTTActivity extends AppCompatActivity {

    private LinearLayout teacherContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa);

        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, R.anim.slide_out_left);
        });

        teacherContainer = findViewById(R.id.teacher_container);
        setupContacts();
        loadGiangVien();
        loadSinhVienCount();
    }

    private void setupContacts() {
        setupContactRow(R.id.contact_phone, R.drawable.ic_info, "Điện thoại", "(024) 3856 XXXX", R.color.card_phongdaotao);
        setupContactRow(R.id.contact_email, R.drawable.ic_email, "Email", "cntt@tlu.edu.vn", R.color.card_tkb);
        setupContactRow(R.id.contact_address, R.drawable.ic_university, "Địa chỉ", "Tầng 3, Nhà A3, 175 Tây Sơn", R.color.card_khoa);
    }

    private void loadSinhVienCount() {
        ApiClient.getApiService(this).getSinhVienCount().enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Object total = response.body().get("total");
                    TextView tvStatSv = findViewById(R.id.tv_stat_sv);
                    if (tvStatSv != null && total != null) {
                        int count = ((Number) total).intValue();
                        tvStatSv.setText(count > 1000 ? String.format("%,d+", count) : String.valueOf(count));
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {}
        });
    }

    private void loadGiangVien() {
        ApiClient.getApiService(this).getGiangVien().enqueue(new Callback<List<GiangVienModel>>() {
            @Override
            public void onResponse(Call<List<GiangVienModel>> call, Response<List<GiangVienModel>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    displayGiangVien(response.body());
                } else {
                    setupMockTeachers();
                }
            }

            @Override
            public void onFailure(Call<List<GiangVienModel>> call, Throwable t) {
                setupMockTeachers();
            }
        });
    }

    private void displayGiangVien(List<GiangVienModel> list) {
        // Cập nhật số thống kê
        TextView tvStatGv = findViewById(R.id.tv_stat_gv);
        if (tvStatGv != null) tvStatGv.setText(String.valueOf(list.size()));

        // Hiển thị danh sách GV động
        teacherContainer.removeAllViews();

        int[] colors = {R.color.card_tkb, R.color.card_khoa, R.color.card_phongdaotao,
                R.color.card_bangdiem, R.color.card_hoidap, R.color.card_thongtin};

        for (int i = 0; i < list.size(); i++) {
            GiangVienModel gv = list.get(i);
            View row = getLayoutInflater().inflate(R.layout.item_teacher_row, teacherContainer, false);

            ((TextView) row.findViewById(R.id.tv_teacher_name)).setText(gv.getFullName());
            String role = gv.getDonVi() != null ? gv.getDonVi() : "Giảng viên";
            if (gv.getEmail1() != null && !gv.getEmail1().isEmpty()) {
                role += " | " + gv.getEmail1();
            }
            ((TextView) row.findViewById(R.id.tv_teacher_role)).setText(role);

            GradientDrawable bg = new GradientDrawable();
            bg.setShape(GradientDrawable.OVAL);
            bg.setColor(ContextCompat.getColor(this, colors[i % colors.length]));
            row.findViewById(R.id.teacher_avatar_bg).setBackground(bg);

            teacherContainer.addView(row);
        }
    }

    private void setupMockTeachers() {
        // Fallback
    }

    private void setupContactRow(int viewId, int iconRes, String label, String value, int bgColorRes) {
        View row = findViewById(viewId);
        ((ImageView) row.findViewById(R.id.iv_contact_icon)).setImageResource(iconRes);
        ((TextView) row.findViewById(R.id.tv_contact_label)).setText(label);
        ((TextView) row.findViewById(R.id.tv_contact_value)).setText(value);

        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setColor(ContextCompat.getColor(this, bgColorRes));
        row.findViewById(R.id.contact_icon_bg).setBackground(bg);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_left);
    }
}
