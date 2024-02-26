package com.vicsoft.basicapi8.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vicsoft.basicapi8.domain.UserInfo;
import com.vicsoft.basicapi8.entity.user.UserEntity;
import com.vicsoft.basicapi8.exception.UserNotFoundException;
import com.vicsoft.basicapi8.repository.user.UserRepository;
import com.vicsoft.basicapi8.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private static final ObjectMapper om = new ObjectMapper();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> userInfo(HttpServletRequest httpServletRequest, String id) throws UserNotFoundException {
        Optional<UserEntity> isExistUser = userRepository.findByIdentifier(id);
        if (!isExistUser.isPresent()) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "[" + id + "] User Not Found", httpServletRequest.getServletPath());
        }

        UserEntity userEntity = isExistUser.get();

        UserInfo userInfo = UserInfo.builder()
                .id(id)
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .age(userEntity.getAge())
                .build();

        return om.convertValue(userInfo, new TypeReference<Map<String, Object>>() {});
    }

    public void insertUser(String id, Map<String, Object> body) throws NoSuchAlgorithmException {
        UserEntity user = UserEntity.builder()
                .identifier(id)
                .password(EncryptUtil.encrypt((String) body.get("password")))
                .name((String) body.get("name"))
                .age((int) body.get("age"))
                .registDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public void updateUser(HttpServletRequest httpServletRequest, String id, Map<String, Object> body) throws NoSuchAlgorithmException, UserNotFoundException {
        Optional<UserEntity> isExistUser = userRepository.findByIdentifier(id);
        if (!isExistUser.isPresent()) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "[" + id + "] User Not Found", httpServletRequest.getServletPath());
        }

        UserEntity user = isExistUser.get();
        if (body.get("password") != null) {
            user.setPassword(EncryptUtil.encrypt((String) body.get("password")));
        }
        if (body.get("name") != null) {
            user.setName((String) body.get("name"));
        }
        if (body.get("age") != null) {
            user.setAge((int) body.get("age"));
        }
        user.setModifyDate(LocalDateTime.now());

        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteByIdentifier(id);
    }

}
