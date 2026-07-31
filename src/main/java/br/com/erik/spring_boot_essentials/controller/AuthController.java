package br.com.erik.spring_boot_essentials.controller;

import br.com.erik.spring_boot_essentials.dto.LoginRequestDto;
import br.com.erik.spring_boot_essentials.dto.RegisterRequestDto;
import br.com.erik.spring_boot_essentials.dto.TokenResponseDto;
import br.com.erik.spring_boot_essentials.exception.BadRequestException;
import br.com.erik.spring_boot_essentials.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDto registerRequestDto) throws BadRequestException {
        authenticationService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws BadRequestException {
        return authenticationService.login(loginRequestDto);
    }
}
