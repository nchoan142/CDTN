package com.conghoan.sinhviencntt.config;

import com.conghoan.sinhviencntt.entity.TaiKhoanAdmin;
import com.conghoan.sinhviencntt.repository.TaiKhoanAdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final TaiKhoanAdminRepository repo;

    public AdminUserDetailsService(TaiKhoanAdminRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoanAdmin admin = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy: " + username));
        return new User(admin.getUsername(), admin.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + admin.getRole())));
    }
}
