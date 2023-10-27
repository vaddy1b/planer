package ru.babich.planer.controller;

import jakarta.validation.Valid;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.babich.planer.exception.UserExistException;
import ru.babich.planer.payload.request.LoginRequest;
import ru.babich.planer.payload.request.SignUpRequest;
import ru.babich.planer.payload.response.JwtTokenSuccessResponse;
import ru.babich.planer.payload.response.MessageResponse;
import ru.babich.planer.security.JwtTokenProvider;
import ru.babich.planer.security.SecurityConstants;
import ru.babich.planer.servise.UserServise;
import ru.babich.planer.validations.ResponseErrorValidator;

@CrossOrigin
@RestController
@RequestMapping(ControllerConstant.AUTH_PATH)
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private JwtTokenProvider token;

    @Autowired
    private ResponseErrorValidator responseValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServise userServise;


    public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest,
                                                     BindingResult bindingResult) {
        ResponseEntity <Object> errors = responseValidator.mapValidationServise(bindingResult);

        if(!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = SecurityConstants.TOKEN_PREFIX + token.generateToken(authentication);
        return ResponseEntity.ok(new JwtTokenSuccessResponse(true, jwt));
    }



    /**
     * register new user
     * */
    @PostMapping("/signup")
    public ResponseEntity <Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                                BindingResult bindingResult) throws UserExistException {

        ResponseEntity <Object> errors = responseValidator.mapValidationServise(bindingResult);

        if(!ObjectUtils.isEmpty(errors)) return errors;

        userServise.createUser(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }


}
