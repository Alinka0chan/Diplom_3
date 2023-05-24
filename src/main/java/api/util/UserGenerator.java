package api.util;

import api.model.User;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User getRandom(int countPassword) {
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        final String password = RandomStringUtils.randomAlphabetic(countPassword);
        return new User(name, email, password);
    }
}
