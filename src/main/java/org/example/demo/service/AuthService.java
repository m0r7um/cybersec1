package org.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.dto.AuthRequest;
import org.example.demo.entity.AuthModel;
import org.example.demo.exceptions.IncorrectPasswordException;
import org.example.demo.exceptions.UserAlreadyExistsException;
import org.example.demo.exceptions.UserNotFoundException;
import org.example.demo.repository.AuthRepository;
import org.example.demo.utils.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String register(AuthRequest authRequest) {
        Optional<AuthModel> user = authRepository.findById(authRequest.login());
        if (user.isPresent()) throw new UserAlreadyExistsException("Пользователь с никнеймом " + authRequest.login() + " уже существует");

        AuthModel authModel = new AuthModel(
                HtmlUtils.htmlEscape(authRequest.login()),
                passwordEncoder.encode(authRequest.password())
        );
        authRepository.save(authModel);
        return jwtUtils.generateJwtToken(authModel.getLogin());
    }

    public String login(AuthRequest authRequest) {
        AuthModel user = authRepository.findById(authRequest.login())
                .orElseThrow(() -> new UserNotFoundException("Пользователь с никнеймом " + authRequest.login() + " не найден"));
        if (!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        return jwtUtils.generateJwtToken(user.getLogin());
    }
}
