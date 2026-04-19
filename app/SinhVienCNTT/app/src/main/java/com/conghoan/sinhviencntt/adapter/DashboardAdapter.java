package com.conghoan.sinhviencntt.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.conghoan.sinhviencntt.R;
import com.conghoan.sinhviencntt.model.DashboardItem;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private List<DashboardItem> items;
    private Context context;
    private int lastAnimatedPosition = -1;

    public DashboardAdapter(Context context, List<DashboardItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashboardItem item = items.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvSubtitle.setText(item.getSubtitle());
        holder.ivIcon.setImageResource(item.getIconResId());

        int bgColor = ContextCompat.getColor(context, item.getBgColorResId());
        int iconColor = ContextCompat.getColor(context, item.getIconColorResId());

        // Set card background with slight tint
        holder.cardView.setCardBackgroundColor(blendWithWhite(bgColor, 0.45f));

        // Set icon circle background color
        GradientDrawable circleDrawable = new GradientDrawable();
        circleDrawable.setShape(GradientDrawable.OVAL);
        circleDrawable.setColor(bgColor);
        holder.iconCircle.setBackground(circleDrawable);

        // Decorative blob - same color, more transparent
        GradientDrawable blobDrawable = new GradientDrawable();
        blobDrawable.setShape(GradientDrawable.OVAL);
        blobDrawable.setColor(bgColor);
        holder.decorBlob.setBackground(blobDrawable);

        // Decorative dot
        GradientDrawable dotDrawable = new GradientDrawable();
        dotDrawable.setShape(GradientDrawable.OVAL);
        dotDrawable.setColor(bgColor);
        holder.decorDot.setBackground(dotDrawable);

        // Accent line at bottom
        GradientDrawable lineDrawable = new GradientDrawable();
        lineDrawable.setShape(GradientDrawable.RECTANGLE);
        lineDrawable.setCornerRadius(4f);
        lineDrawable.setColor(iconColor);
        holder.accentLine.setBackground(lineDrawable);

        // Arrow color
        holder.tvArrow.setTextColor(iconColor);

        // Click listener
        holder.itemView.setOnClickListener(v -> {
            if (item.getTargetActivity() != null) {
                Intent intent = new Intent(context, item.getTargetActivity());
                intent.putExtra("title", item.getTitle());
                context.startActivity(intent);
            }
        });

        // Animate items with stagger
        if (position > lastAnimatedPosition) {
            holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_in));
            holder.itemView.getAnimation().setStartOffset(position * 100L);
            lastAnimatedPosition = position;
        }
    }

    private int blendWithWhite(int color, float ratio) {
        int r = (int) (Color.red(color) + (255 - Color.red(color)) * ratio);
        int g = (int) (Color.green(color) + (255 - Color.green(color)) * ratio);
        int b = (int) (Color.blue(color) + (255 - Color.blue(color)) * ratio);
        return Color.rgb(r, g, b);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivIcon;
        TextView tvTitle, tvSubtitle, tvArrow;
        View iconCircle, decorBlob, decorDot, accentLine;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubtitle = itemView.findViewById(R.id.tv_subtitle);
            tvArrow = itemView.findViewById(R.id.tv_arrow);
            iconCircle = itemView.findViewById(R.id.icon_circle);
            decorBlob = itemView.findViewById(R.id.decor_blob);
            decorDot = itemView.findViewById(R.id.decor_dot);
            accentLine = itemView.findViewById(R.id.accent_line);
        }
    }
}
