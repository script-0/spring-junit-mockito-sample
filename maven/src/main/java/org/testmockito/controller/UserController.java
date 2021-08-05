package org.testmockito.controller;

import org.testmockito.model.User;
import org.testmockito.rest.RestResponse;
import org.testmockito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
