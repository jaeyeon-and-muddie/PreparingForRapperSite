package com.skhu.practice.service;

import com.skhu.practice.dto.ArtistDto;
import com.skhu.practice.dto.UserSignupDto;
import com.skhu.practice.dto.VisitedResponseDto;
import com.skhu.practice.entity.Users;
import com.skhu.practice.entity.Visited;
import com.skhu.practice.repository.UserRepository;
import com.skhu.practice.repository.VisitedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final VisitedRepository visitedRepository;

    private final UrlToTitleService urlToTitleService;

    private final int VISITED_SIZE_LIMIT = 5;

    public boolean isNotEqualsPassword1AndPassword2(UserSignupDto userSignupDto) {
        return !userSignupDto.getPassword1().equals(userSignupDto.getPassword2());
    }

    public void save(UserSignupDto userSignupDto) {
        userRepository.save(Users.builder()
                .username(userSignupDto.getUsername())
                .email(userSignupDto.getEmail())
                .password(passwordEncoder.encode(userSignupDto.getPassword1()))
                .build());
    }

    public void updateVisited(String username, String url) { // String username 을 그대로 받은다음 저장한다.
        String titleOfUrl = urlToTitleService.getTitleByUrl(url); // 현재 상세 페이지 이름을 받는다.
        Users users = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        Visited insertVisit = Visited.builder()
                .title(titleOfUrl)
                .url(url)
                .users(users)
                .build();
        List<Visited> visited = users.getVisited();
        System.out.println(users.getVisited());
        calculateVisit(insertVisit, visited);
    }

    private void calculateVisit(Visited insertVisit, List<Visited> visited) {
        if (visited.contains(insertVisit)) {
            int removeIndex = findRemoveIndex(insertVisit, visited);

            if (!(visited.size() == VISITED_SIZE_LIMIT && removeIndex == 0)) {
                visitedRepository.delete(visited.get(removeIndex));
                visited.remove(removeIndex);
            }
        }

        visitedRepository.save(insertVisit);
        System.out.println(visited);

        if (visited.size() > VISITED_SIZE_LIMIT) {
            visitedRepository.delete(visited.get(0));
        }
    }

    private int findRemoveIndex(Visited insertVisit, List<Visited> visited) {
        int removeIndex = 0;

        for (int index = 0; index < visited.size(); index++) {
            if (visited.get(index).equals(insertVisit)) {
                removeIndex = index;
            }
        }

        return removeIndex;
    }

    public List<VisitedResponseDto> userVisited(Principal principal, String url) {
        if (principal != null) {
            updateVisited(principal.getName(), url);
            return userRepository.findByUsername(principal.getName())
                    .orElseThrow(NoSuchElementException::new)
                    .getVisited()
                    .stream()
                    .map(Visited::toResponseDto)
                    .collect(Collectors.toList());
        }

        return null;
    }

    public ArtistDto findById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new)
                .toArtistDto();
    }
}
