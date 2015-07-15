package com.goodtimes.users;

import com.goodtimes.support.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody GoodtimesUser user) {
        GoodtimesUser savedUser = saveUser(user);
        return new ResponseEntity<>(null,
                HttpUtil.createPostHttpHeaders(savedUser.getId()),
                HttpStatus.CREATED);
    }

    private GoodtimesUser saveUser(@RequestBody GoodtimesUser user) {
        GoodtimesUser.GoodtimesUserBuilder userBuilder = GoodtimesUser.createBuilderFrom(user);
        enableUser(userBuilder);
        userBuilder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
        return usersRepository.save(userBuilder.build());
    }

    private void enableUser(GoodtimesUser.GoodtimesUserBuilder userBuilder) {
                userBuilder.enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("username") String username) {
        usersRepository.removeByUsername(username);
    }


}
