package com.conghoan.sinhviencntt.controller;

import com.conghoan.sinhviencntt.repository.CoVanHocTapRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/covanhoctap")
public class CoVanHocTapController {

    private final CoVanHocTapRepository repo;

    public CoVanHocTapController(CoVanHocTapRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{maKy}")
    public ResponseEntity<?> getByKy(@PathVariable String maKy) {
        return ResponseEntity.ok(repo.findByMaKy(maKy));
    }

    @GetMapping("/giangvien/{maGv}")
    public ResponseEntity<?> getByGv(@PathVariable String maGv) {
        return ResponseEntity.ok(repo.findByMaGiangVien(maGv));
    }

    @GetMapping("/lop/{maLop}")
    public ResponseEntity<?> getByLop(@PathVariable String maLop) {
        return ResponseEntity.ok(repo.findByMaLop(maLop));
    }
}
