package com.project.uber.services;

import com.project.spring_mvc.exceptions.ResourceNotFoundException;
import com.project.uber.entities.User;
import com.project.uber.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username);
    }

    public User getUserById(Long userId)
    {
        return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));
    }
}
