package ru.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private final String SUCCESS_LOGIN_FORM = "/admin/create";


    @GetMapping("/login")
    public ModelAndView getLoginForm() {
        return new ModelAndView("/login_form.jsp");
    }

    @PostMapping("/authorize")
    public ModelAndView getLoginForm(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam("login") String login,
                                     @RequestParam("password") String password) throws IOException {
        HttpSession session = request.getSession();

        AuthResult authResult = authenticationManager.authorize(login, password);
        if ("OK".equals(authResult.getStatus())) {
            session.setAttribute("authObject", "AUTHENTICATED");
            response.sendRedirect(SUCCESS_LOGIN_FORM);
            return null;
        } else {
            session.removeAttribute("authObject");
            ModelAndView loginForm = new ModelAndView("/login_form.jsp");
            loginForm.addObject("massage", authResult.getStatus());
            return loginForm;
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("authObject");
        response.sendRedirect(SUCCESS_LOGIN_FORM);
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
