package in.sdqali.spring.csrf.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/login")
public class LoginController {
  @RequestMapping(method = POST,
      path = "",
      produces = APPLICATION_JSON_VALUE)
  public Map<String, Object> login(Authentication auth) {
    return Collections.singletonMap("user", auth.getName());
  }
}
