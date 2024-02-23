package com.vicsoft.basicapi8.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vicsoft.basicapi8.domain.UserInfo;
import com.vicsoft.basicapi8.entity.user.UserEntity;
import com.vicsoft.basicapi8.exception.UserNotFoundException;
import com.vicsoft.basicapi8.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

}
