package ru.ssau.labs.museumcatalogbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
public class AuthenticatedUserDto {

    private String accessToken;
    private String username;
    private List<String> roles;
}
