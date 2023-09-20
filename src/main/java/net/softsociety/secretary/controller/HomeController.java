package net.softsociety.secretary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.softsociety.secretary.domain.CustomUserDetails;
import net.softsociety.secretary.domain.User;
import net.softsociety.secretary.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
    private UserService userService;  
	/**
	 * 메인화면!
	 */
	@GetMapping({"", "/"})
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Check if the principal is a CustomUserDetails
            if (authentication.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                if ("ADMIN".equals(userDetails.getUser().getRolename())) {
                    // Redirect to the admin page
                    return "redirect:/adminView/adminHome";
                }
            }
        }
		return "homeView/home";
	}
	
}
