package com.skhu.practice.Configuration;
import com.skhu.practice.Sevice.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)//secured 어노테이션 활성화, preAuthorize라는 어노테이션 활성화, postAuthorize라는 어노테이션 활성화
public class SecurityConfig {
    //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
//
//        return auth.build();
//    }
//    @Bean
//    public BCryptPasswordEncoder encodePwd() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/plusSong", "/plusAlbum").access("hasRole('ADMIN')")
                .antMatchers("/**").permitAll()//인증만 되면 들어갈수 있는 주소!
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email") // 아이디 파라미터명 설정, default: username
                .passwordParameter("password")
                .loginProcessingUrl("/loginProc")// /login주소가 호출이되면 시큐리티가 낚아채서 대신 로그인을 진행
                .defaultSuccessUrl("/home");
        return http.build();
    }
}