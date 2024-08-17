package com.ing.stockexchange.v1.controller.auth;

import com.ing.stockexchange.v1.service.user.AuthenticationService;
import com.ing.stockexchange.v1.auth.RegisterRequest;
import com.ing.stockexchange.v1.auth.UserRequest;
import com.ing.stockexchange.v1.dto.user.UserDTO;
import com.ing.stockexchange.v1.model.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> authenticate(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(userRequest));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        return ResponseEntity.ok("Log out successful!");
    }

}
