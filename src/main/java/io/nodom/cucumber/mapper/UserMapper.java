package io.nodom.cucumber.mapper;

import io.nodom.cucumber.domain.UserEntity;
import io.nodom.cucumber.dto.User;

public class UserMapper {

    public static User mapUserEntityToUser(UserEntity userEntity) {
        return User.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();
    }

    public static UserEntity mapUserToUserEntity(User user) {
        return UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
