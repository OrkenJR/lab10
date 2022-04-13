package kz.iitu.itse_1904.naga.util;

import kz.iitu.itse_1904.naga.database.User;
import org.springframework.core.convert.converter.Converter;

public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        String[] data = source.split(";");

        return User.builder()
                .email(data[0])
                .firstName(data[1])
                .lastName(data[2])
                .phone(data[3])
                .username(data[4])
                .password(data[5])
                .build();
    }
}
