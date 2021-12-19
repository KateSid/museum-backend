package ru.ssau.labs.museumcatalogbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.labs.museumcatalogbackend.dao.UserDao;
import ru.ssau.labs.museumcatalogbackend.domain.User;
import ru.ssau.labs.museumcatalogbackend.dto.AuthenticatedUserDto;
import ru.ssau.labs.museumcatalogbackend.dto.UserDto;
import ru.ssau.labs.museumcatalogbackend.enums.RoleEnum;
import ru.ssau.labs.museumcatalogbackend.mapper.UserMapper;
import ru.ssau.labs.museumcatalogbackend.security.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;

    @PostMapping("/signin")
    public AuthenticatedUserDto authenticateUser(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String generateJwt = jwtUtils.generateJwt(authentication);
        log.info(generateJwt);
        AuthenticatedUserDto authenticatedUserDto = getAuthenticatedUserDto(userDto, authentication, generateJwt);
        return authenticatedUserDto;
    }

    private AuthenticatedUserDto getAuthenticatedUserDto(UserDto userDto, Authentication authentication, String generateJwt) {
        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto();
        authenticatedUserDto.setUsername(userDto.getUsername());
        authenticatedUserDto.setAccessToken(generateJwt);
        authenticatedUserDto.setRoles(getRoles(authentication));
        return authenticatedUserDto;
    }

    private List<String> getRoles(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registrationUser(@RequestBody UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.toEntity(userDto);
        user.setRole(RoleEnum.ROLE_USER);
        userDao.save(user);
    }
}
