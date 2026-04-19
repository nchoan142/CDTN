package com.conghoan.sinhviencntt.controller;

import com.conghoan.sinhviencntt.repository.DanhMucRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/danhmuc")
public class DanhMucController {

    private final DanhMucRepository repo;

    public DanhMucController(DanhMucRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(repo.findByDangDungTrue());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncludeDisabled() {
        return ResponseEntity.ok(repo.findAll());
    }
}
