package com.example.refrigerator.service;

import com.example.refrigerator.domain.User;
import com.example.refrigerator.dto.FindPwdDto;
import com.example.refrigerator.dto.LoginRequestDto;
import com.example.refrigerator.dto.UserRequestDto;
import com.example.refrigerator.dto.UserResponseDto;
import com.example.refrigerator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    @Transactional
    public User save(UserRequestDto userRequestDto) {
        // 기존 유저인지 확인
        User exUsername = userRepository.findUserByName(userRequestDto.getUsername());
        User exEmail = userRepository.findUserByEmail(userRequestDto.getEmail());
        if (exUsername != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"이미 존재하는 이름 혹은 ID 입니다.");
        } else if (exEmail != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"이미 존재하는 이메일 입니다.");
        }
        return userRepository.save(userRequestDto.toEntity());
    }

    @Transactional
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        User exUser = userRepository.findUserByName(loginRequestDto.getUsername());

        if (exUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 회원이 없습니다. username = "+ loginRequestDto.getUsername());
        }
        if (!exUser.getPassword().equals(loginRequestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"비밀번호가 틀립니다. ");
        }
        return new UserResponseDto(exUser);
    }

    public String findPassword(String username) {
        User exUser = userRepository.findUserByName(username);
        if (exUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 회원이 없습니다. username = "+ username);
        }
        return exUser.getPassword();
    }

    public UserResponseDto findOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 회원이 없습니다. id=" + id));
        return new UserResponseDto(user);
    }

    public List<UserResponseDto> findALl() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponseDto(user)).collect(Collectors.toList());
    }
}
