package dw.into.controller;

import dw.into.dto.SessionDto;
import dw.into.dto.UserDto;
import dw.into.model.User;
import dw.into.service.UserDetailService;
import dw.into.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private UserDetailService userDetailService;
    private AuthenticationManager authenticationManager;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public UserController(UserService userService, UserDetailService userDetailService, AuthenticationManager authenticationManager, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserId(), userDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        User authenticatedUser = (User) authentication.getPrincipal();
        SessionDto sessionDto = new SessionDto(
                authenticatedUser.getUserId(),
                authenticatedUser.getNickname(),
                authenticatedUser.getAuthorities()
        );

        session.setAttribute("sessionDto", sessionDto);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/current")
    public SessionDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUserId(authentication.getName());
        sessionDto.setAuthority(authentication.getAuthorities());
        return sessionDto;
    }
}
