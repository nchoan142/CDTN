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
import com.conghoan.sinhviencntt.model.BieuMauModel;
import com.conghoan.sinhviencntt.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongDaoTaoActivity extends AppCompatActivity {

    private LinearLayout formContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phongdaotao);

        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, R.anim.slide_out_left);
        });

        formContainer = findViewById(R.id.form_container);
        loadBieuMau();
    }

    private void loadBieuMau() {
        ApiClient.getApiService(this).getBieuMau().enqueue(new Callback<List<BieuMauModel>>() {
            @Override
            public void onResponse(Call<List<BieuMauModel>> call, Response<List<BieuMauModel>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    displayBieuMau(response.body());
                } else {
                    setupMockForms();
                }
            }

            @Override
            public void onFailure(Call<List<BieuMauModel>> call, Throwable t) {
                setupMockForms();
            }
        });
    }

    private void displayBieuMau(List<BieuMauModel> list) {
        if (formContainer == null) {
            setupMockForms();
            return;
        }
        formContainer.removeAllViews();

        int[] icons = {R.drawable.ic_grade, R.drawable.ic_schedule, R.drawable.ic_info,
                R.drawable.ic_training, R.drawable.ic_faculty, R.drawable.ic_qa};
        int[] colors = {R.color.card_bangdiem, R.color.card_tkb, R.color.card_thongtin,
                R.color.card_phongdaotao, R.color.card_khoa, R.color.card_hoidap};

        for (int i = 0; i < list.size(); i++) {
            BieuMauModel bm = list.get(i);
            View row = getLayoutInflater().inflate(R.layout.item_form_row, formContainer, false);
            ((ImageView) row.findViewById(R.id.iv_form_icon)).setImageResource(icons[i % icons.length]);
            ((TextView) row.findViewById(R.id.tv_form_name)).setText(bm.getTenBieuMau());
            ((TextView) row.findViewById(R.id.tv_form_desc)).setText(bm.getMoTa());

            GradientDrawable bg = new GradientDrawable();
            bg.setShape(GradientDrawable.OVAL);
            bg.setColor(ContextCompat.getColor(this, colors[i % colors.length]));
            row.findViewById(R.id.form_icon_bg).setBackground(bg);

            formContainer.addView(row);
        }
    }

    private void setupMockForms() {
        if (formContainer == null) return;
        formContainer.removeAllViews();
        addMockForm(R.drawable.ic_grade, "Đơn xin nghỉ học tạm thời", "Mẫu BM-01", R.color.card_bangdiem);
        addMockForm(R.drawable.ic_schedule, "Đơn đăng ký học phần bổ sung", "Mẫu BM-02", R.color.card_tkb);
        addMockForm(R.drawable.ic_info, "Đơn xin xác nhận sinh viên", "Mẫu BM-03", R.color.card_thongtin);
        addMockForm(R.drawable.ic_training, "Đơn xin chuyển ngành", "Mẫu BM-04", R.color.card_phongdaotao);
    }

    private void addMockForm(int iconRes, String name, String desc, int bgColorRes) {
        View row = getLayoutInflater().inflate(R.layout.item_form_row, formContainer, false);
        ((ImageView) row.findViewById(R.id.iv_form_icon)).setImageResource(iconRes);
        ((TextView) row.findViewById(R.id.tv_form_name)).setText(name);
        ((TextView) row.findViewById(R.id.tv_form_desc)).setText(desc);

        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setColor(ContextCompat.getColor(this, bgColorRes));
        row.findViewById(R.id.form_icon_bg).setBackground(bg);

        formContainer.addView(row);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_left);
    }
}
