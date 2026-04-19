package com.conghoan.sinhviencntt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hoi_dap")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HoiDap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maSinhVien;
    private String tenSinhVien;

    @Column(columnDefinition = "TEXT")
    private String cauHoi;

    @Column(columnDefinition = "TEXT")
    private String cauTraLoi;

    private String nguoiTraLoi;
    private LocalDateTime ngayHoi;
    private LocalDateTime ngayTraLoi;
    private Boolean daDuyet;
}
