package com.projectdave.habitrack.service;

import com.projectdave.habitrack.model.User;
import com.projectdave.habitrack.model.UserVerificationEntity;
import com.projectdave.habitrack.repository.UserRepository;
import com.projectdave.habitrack.repository.UserVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserVerificationRepository userVerificationRepository;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow();
        UserVerificationEntity userVerificationEntity = userVerificationRepository.findByUserId(user.getId()).orElseThrow();

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(userVerificationEntity.getVerificationCode())
                        .roles(user.getRole().toString())
                        .build();
        return userDetails;
    }
}