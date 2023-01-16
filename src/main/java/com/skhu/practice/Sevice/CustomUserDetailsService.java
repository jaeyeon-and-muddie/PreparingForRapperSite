package com.skhu.practice.Sevice;


import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final HttpSession session;


    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(Email).orElseThrow(()->
                new UsernameNotFoundException("해당 사용자 없음.:"+Email));
        session.setAttribute("user", new UserSessionDto(user));

        return new CustomUserDetails(user);
    }

}
