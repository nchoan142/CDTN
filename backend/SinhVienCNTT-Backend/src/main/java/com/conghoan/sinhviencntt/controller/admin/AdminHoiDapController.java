package com.conghoan.sinhviencntt.controller.admin;

import com.conghoan.sinhviencntt.entity.HoiDap;
import com.conghoan.sinhviencntt.repository.HoiDapRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/hoidap")
public class AdminHoiDapController {

    private final HoiDapRepository repo;

    public AdminHoiDapController(HoiDapRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", repo.findAllByOrderByNgayHoiDesc());
        return "admin/hoidap-list";
    }

    @GetMapping("/chuaduyet")
    public String chuaDuyet(Model model) {
        model.addAttribute("list", repo.findByDaDuyetFalseOrderByNgayHoiDesc());
        return "admin/hoidap-list";
    }

    @GetMapping("/traloi/{id}")
    public String traLoiForm(@PathVariable Long id, Model model) {
        model.addAttribute("hoiDap", repo.findById(id).orElseThrow());
        return "admin/hoidap-form";
    }

    @PostMapping("/traloi")
    public String traLoi(@ModelAttribute HoiDap hoiDap, RedirectAttributes ra) {
        HoiDap existing = repo.findById(hoiDap.getId()).orElseThrow();
        existing.setCauTraLoi(hoiDap.getCauTraLoi());
        existing.setNguoiTraLoi(hoiDap.getNguoiTraLoi());
        existing.setNgayTraLoi(LocalDateTime.now());
        existing.setDaDuyet(true);
        repo.save(existing);
        ra.addFlashAttribute("success", "Đã trả lời và duyệt câu hỏi!");
        return "redirect:/admin/hoidap";
    }

    @GetMapping("/duyet/{id}")
    public String duyet(@PathVariable Long id, RedirectAttributes ra) {
        HoiDap hd = repo.findById(id).orElseThrow();
        hd.setDaDuyet(true);
        repo.save(hd);
        ra.addFlashAttribute("success", "Đã duyệt câu hỏi!");
        return "redirect:/admin/hoidap";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        repo.deleteById(id);
        ra.addFlashAttribute("success", "Đã xoá câu hỏi!");
        return "redirect:/admin/hoidap";
    }
}
