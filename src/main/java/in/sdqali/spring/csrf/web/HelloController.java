package in.sdqali.spring.csrf.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/hello")
public class HelloController {
  @RequestMapping(method = GET,
      path = "",
      produces = APPLICATION_JSON_VALUE)
  public Map<String, String> hello() {
    return Collections.singletonMap("message", "hello");
  }
}
