package app.service;

import app.model.User;
//import org.springframework.data.repository.CrudRepository;

//import java.util.List;

public interface UserService{
    public User getUser(int id);

    public void saveUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);
}
