package com.conghoan.sinhviencntt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "thoi_khoa_bieu")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ThoiKhoaBieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maSinhVien;
    private String maHocPhan;
    private String tenHocPhan;
    private String maGiangVien;
    private String tenGiangVien;
    private String phongHoc;
    private Integer thu;
    private String tietBatDau;
    private String tietKetThuc;
    private String gioBatDau;
    private String gioKetThuc;
    private String maKy;
    private String nhom;
}
