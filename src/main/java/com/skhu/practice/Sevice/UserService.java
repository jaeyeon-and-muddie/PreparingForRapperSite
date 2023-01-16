package com.skhu.practice.Sevice;
import com.skhu.practice.DTO.UserDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(UserDto dto){
        dto.setPassword(encoder.encode(dto.getPassword()));
        return userRepository.save(dto.toEntity()).getId();
    }

//    private final String LOGIN_SUCCESS = "home";
//    private final String LOGIN_FAILED = "login";

//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public String login(UserLoginDto userLoginDto) {
//        User user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
//                .orElse(null);
//
//        if (user == null) {
//            return LOGIN_FAILED;
//        }
//
//        return LOGIN_SUCCESS;
//    }
//---------------------------------------------------------------------------------------------------

}