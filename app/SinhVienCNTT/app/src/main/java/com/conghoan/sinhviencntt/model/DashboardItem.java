package com.conghoan.sinhviencntt.model;

public class DashboardItem {
    private String title;
    private String subtitle;
    private int iconResId;
    private int bgColorResId;
    private int iconColorResId;
    private Class<?> targetActivity;

    public DashboardItem(String title, String subtitle, int iconResId, int bgColorResId, int iconColorResId, Class<?> targetActivity) {
        this.title = title;
        this.subtitle = subtitle;
        this.iconResId = iconResId;
        this.bgColorResId = bgColorResId;
        this.iconColorResId = iconColorResId;
        this.targetActivity = targetActivity;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getBgColorResId() {
        return bgColorResId;
    }

    public int getIconColorResId() {
        return iconColorResId;
    }

    public Class<?> getTargetActivity() {
        return targetActivity;
    }
}
