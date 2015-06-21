package com.goodtimes.users;

import com.goodtimes.users.GoodtimesUser;
import com.goodtimes.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class AdminUserCreator {

    @Value("${admin.username}")
    public String adminUsername;
    @Value("${admin.password}")
    public String adminPassword;
    @Value("${admin.email}")
    public String adminEmail;

    private final UsersRepository usersRepository;

    @Autowired
    public AdminUserCreator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostConstruct
    public void create() {
        GoodtimesUser existingAdminUser = usersRepository.findByUsername(adminUsername);
        GoodtimesUser.GoodtimesUserBuilder userBuilder = createAdminUserBuilder();
        if(existingAdminUser != null) {
            userBuilder.id(existingAdminUser.getId());
        }
        usersRepository.save(userBuilder.build());
    }

    private GoodtimesUser.GoodtimesUserBuilder createAdminUserBuilder() {
        return GoodtimesUser.builder()
                .username(adminUsername)
                .password(adminPassword)
                .email(adminEmail)
                .roles(Collections.singletonList("ADMIN"));
    }
}
