package com.boulow.mono.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.boulow.mono.entity.User;
import com.boulow.mono.entity.dto.UserDTO;
import com.boulow.mono.entity.dto.UserUpdateDTO;
import com.boulow.mono.exception.ResourceNotFoundException;
import com.boulow.mono.exception.UnauthorizedException;
import com.boulow.mono.service.UserService;
import com.boulow.mono.service.mapper.UserMapper;

import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/users")
@Slf4j
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized."),
        @ApiResponse(code = 500, message = "The server is down. Please make sure that the User Microservice is running.")
})
public class UserController {
	
	@Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UserDTO updateUser(@ModelAttribute UserUpdateDTO toUser) {
        log.info("Inside updateUser method of UserController");
        User fromUser = userService.findByEmail(toUser.getEmail()).orElse(null);
        if(fromUser != null) {
        	return userMapper.toDto(userService.update(fromUser, toUser));
        } else
        	throw new UnauthorizedException("This action is not authorized or a problem was encountered during its execution");
    }
    
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signUp(@AuthenticationPrincipal User user) {
        log.info("Inside signUp method of UserController");
        UserDTO userDto = new UserDTO();
        User tmp = userService.findByEmail(user.getEmail()).orElse(null);
        if(tmp == null)
        	userDto = userMapper.toDto(userService.save(user));
        else
        	userDto = userMapper.toDto(tmp);
        return userDto;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        log.info("Inside getAllUsers method of UserController");
        return userService.findAll().stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserDTO findByUserId(@PathVariable("userId") Long userId) {
        log.info("Inside findByUserId method of UserController");
        return userMapper.toDto(userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + "couldn't be found!")));
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userService.delete(userId);
        return "User with ID " + userId + " was deleted successfully!";
    }

}
