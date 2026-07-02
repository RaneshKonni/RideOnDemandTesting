package utilities;

import mapper.Role;
import mapper.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomDataGeneratorUtil {
    public static Random random = new Random();

    public static User getRandomUser(Role role) {
        User user = new User(
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5) + "@pss.com",
                RandomStringUtils.randomAlphanumeric(8),
                RandomStringUtils.randomNumeric(10),
                RandomStringUtils.randomAlphabetic(7),
                role
        );
        if (user.getRole().equals("Vendor"))
            user.setShopName(RandomStringUtils.randomAlphabetic(5));
        return user;
    }
}
