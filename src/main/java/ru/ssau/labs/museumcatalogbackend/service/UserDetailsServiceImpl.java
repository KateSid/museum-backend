package ru.ssau.labs.museumcatalogbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ssau.labs.museumcatalogbackend.dao.UserDao;
import ru.ssau.labs.museumcatalogbackend.domain.User;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Нет пользователя с логином: " + login));
        return UserDetailsImpl.fromUser(user);
    }
}
