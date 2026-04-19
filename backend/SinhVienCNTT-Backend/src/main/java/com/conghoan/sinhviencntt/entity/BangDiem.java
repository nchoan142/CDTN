package com.conghoan.sinhviencntt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bang_diem")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BangDiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maSinhVien;
    private String maHocPhan;
    private String tenHocPhan;
    private String maGiangVien;
    private Integer soTinChi;
    private String tienQuyet;
    private String loaiHocPhan;
    private Double diemTongKet;
    private Integer soLanThi;
    private String khoa;
    private String chuyenNganhId;
}
