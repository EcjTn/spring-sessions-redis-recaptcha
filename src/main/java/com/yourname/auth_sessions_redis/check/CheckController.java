package com.yourname.auth_sessions_redis.check;

import com.yourname.auth_sessions_redis.infrastructure.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TEMPORARY FILE -- CHECKING ONLY IF IT WORKS
// DELETE THIS


@RestController
@RequestMapping("/test")
public class CheckController {

    @GetMapping("/check1")
    public String check1() {
        return "Working";
    }

    @GetMapping("/check2")
    public String check2() {

        var sc = SecurityContextHolder.getContext();
        var auth = sc.getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        Long userId = userDetails.getUserId();
        System.out.println("Authorities: " + userDetails.getAuthorities());

        return "user-id-is---" + userId;
    }

}
