package com.conghoan.sinhviencntt.repository;

import com.conghoan.sinhviencntt.entity.BieuMau;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BieuMauRepository extends JpaRepository<BieuMau, Long> {
    List<BieuMau> findByDangDungTrueOrderBySttAsc();
    List<BieuMau> findByLoai(String loai);
}
