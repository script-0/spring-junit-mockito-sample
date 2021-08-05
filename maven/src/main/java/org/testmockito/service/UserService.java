package org.testmockito.service;

import org.testmockito.model.User;

public interface UserService {

    public User getUser(int id);

    public void saveUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

}
