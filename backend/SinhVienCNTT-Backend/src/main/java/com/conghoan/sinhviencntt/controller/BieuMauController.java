package com.conghoan.sinhviencntt.controller;

import com.conghoan.sinhviencntt.repository.BieuMauRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bieumau")
public class BieuMauController {

    private final BieuMauRepository repo;

    public BieuMauController(BieuMauRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(repo.findByDangDungTrueOrderBySttAsc());
    }
}
