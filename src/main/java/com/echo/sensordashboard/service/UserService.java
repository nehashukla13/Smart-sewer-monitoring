package com.echo.sensordashboard.service;

import com.echo.sensordashboard.model.User;
import com.echo.sensordashboard.model.Profile;
import com.echo.sensordashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Transactional
    public User registerUser(User user, Profile profile) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfile(profile);
        profile.setUser(user);
        
        return userRepository.save(user);
    }

    @Transactional
    public User updateProfile(Long userId, Profile profile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.getProfile().setPhone(profile.getPhone());
        user.getProfile().setState(profile.getState());
        user.getProfile().setCity(profile.getCity());
        user.getProfile().setWard(profile.getWard());
        
        return userRepository.save(user);
    }
} 