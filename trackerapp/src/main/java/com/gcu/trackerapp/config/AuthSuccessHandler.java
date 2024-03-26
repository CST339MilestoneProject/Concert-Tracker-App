package com.gcu.trackerapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import com.gcu.trackerapp.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = authentication.getName();
        com.gcu.trackerapp.model.User myUser = userService.findByUsername(username);

        if (myUser != null) {
            session.setAttribute("userId", myUser.getId());
        }

        // super.onAuthenticationSuccess(request, response, authentication);

        response.sendRedirect(request.getContextPath() + "/concerts");
    }


}