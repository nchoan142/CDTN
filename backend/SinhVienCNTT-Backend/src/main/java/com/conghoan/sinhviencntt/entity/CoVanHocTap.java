package com.conghoan.sinhviencntt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "co_van_hoc_tap")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CoVanHocTap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maGiangVien;
    private String maLop;
    private String maKy;
    private String ghiChu;
}
