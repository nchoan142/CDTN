package com.conghoan.sinhviencntt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "danh_muc")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stt;
    private String tenVietTat;
    private String tenDayDu;
    @Column(length = 500)
    private String linkAnh;
    @Column(length = 500)
    private String linkTruyCap;
    private String nguoiQuanLy;
    private Boolean dangDung;
    private Integer danhMucCha;
}
