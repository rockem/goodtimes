package com.goodtimes.users;

import com.goodtimes.support.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        GoodtimesUser savedUser = usersRepository.save(createEnabledUserFrom(user));
        return new ResponseEntity<>(null,
                HttpUtil.createPostHttpHeaders(savedUser.getId().toString()),
                HttpStatus.CREATED);
    }

    private GoodtimesUser createEnabledUserFrom(GoodtimesUser user) {
        return GoodtimesUser.createBuilderFrom(user)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("username") String username) {
        usersRepository.removeByUsername(username);
    }


}
