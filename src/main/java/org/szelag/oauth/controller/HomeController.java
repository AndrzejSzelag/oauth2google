package org.szelag.oauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public String home(@AuthenticationPrincipal OAuth2User oauth2User, final Model model) {
        if (oauth2User != null) {
            model.addAttribute("name", oauth2User.getAttribute("name"));
            model.addAttribute("picture", oauth2User.getAttribute("picture"));
            model.addAttribute("familyName", oauth2User.getAttribute("family_name"));
            model.addAttribute("givenName", oauth2User.getAttribute("given_name"));
            model.addAttribute("email", oauth2User.getAttribute("email"));
            model.addAttribute("iss", oauth2User.getAttribute("iss"));
            return "home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        // null if no further authentication information should be stored
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }
}
