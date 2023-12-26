package com.backend.dtos;

import com.backend.entities.User;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserDto {

    private String username;
    private String password;

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public static UserDto fromEntity(User user) {

        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}
