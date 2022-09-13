package jee.whmanagement.demo.resource;


import jee.whmanagement.demo.config.JwtTokenUtil;
import jee.whmanagement.demo.model.LoginRequest;
import jee.whmanagement.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class AuthenticationResource {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    MyUserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {

        return userService.signin(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
