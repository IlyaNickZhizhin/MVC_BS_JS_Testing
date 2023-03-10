package ru.kata.spring.boot_security.demo.configs;

import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    UserService userService;

    @Autowired
    public SuccessUserHandler(UserService userService) {
        this.userService = userService;
    }

    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else
        if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user/?id="+getUserID(authentication));
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }

     private int getUserID(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userService.getIdByEmail(username);
        }
        return 0;
    }
}