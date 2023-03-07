package com.skhu.practice.Sevice;
import com.skhu.practice.DTO.NavbarDto;
import com.skhu.practice.DTO.UserDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.market.Point;
import com.skhu.practice.Repository.Market.PointRepository;
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
    private final PointRepository pointRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long join(UserDto dto){
        dto.setPassword(encoder.encode(dto.getPassword()));
        Long id = userRepository.save(dto.toEntity()).getId();
        User user = userRepository.findById(id).orElse(null);
        Point point = Point.builder()
                .user(user)
                .build();
        pointRepository.save(point);
        return id;
    }

    public NavbarDto navbar(UserSessionDto user) {
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        Point point = pointRepository.findByUserId(user1.getId());
        NavbarDto navBarDto = NavbarDto.builder()
                .user(user1)
                .point(point)
                .build();
        return navBarDto;

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