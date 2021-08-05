package org.testmockito.service.impl;


import org.testmockito.model.User;
import org.testmockito.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(int id) {
        User user = new User();
        user.setAge(15);
        user.setFirstName("bob");
        user.setId(id);

        return user;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public void updateUser(User user) {

    }
}
