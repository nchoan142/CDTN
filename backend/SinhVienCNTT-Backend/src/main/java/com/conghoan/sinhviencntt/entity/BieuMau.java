package com.conghoan.sinhviencntt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bieu_mau")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BieuMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenBieuMau;
    private String moTa;
    private String maBieuMau;    // BM-01, BM-02...
    @Column(length = 500)
    private String linkTaiVe;
    private String loai;         // DON, MAU, HUONG_DAN
    private Boolean dangDung;
    private Integer stt;
}
