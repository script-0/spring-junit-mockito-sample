package app.controller;

import app.model.User;
import app.service.UserService; 
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;
import app.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
@RestController
public class ServiceController {

//    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // @Autowired
    private UserService userService;

    @Autowired
    public ServiceController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestResponse<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        return new RestResponse<User>(true, "", user);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public RestResponse saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new RestResponse<String>(true, "", null);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestResponse deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new RestResponse<String>(true, "", null);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public RestResponse updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new RestResponse<String>(true, "", null);
    }
}
