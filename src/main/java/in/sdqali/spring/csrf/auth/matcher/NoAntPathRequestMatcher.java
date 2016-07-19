package in.sdqali.spring.csrf.auth.matcher;

import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NoAntPathRequestMatcher implements RequestMatcher {
  private final AndRequestMatcher andRequestMatcher;

  public NoAntPathRequestMatcher(String[] patterns) {
    List<RequestMatcher> requestMatchers = Arrays.asList(patterns)
        .stream()
        .map(p -> new NegatedRequestMatcher(new AntPathRequestMatcher(p)))
        .collect(Collectors.toList());

    andRequestMatcher = new AndRequestMatcher(requestMatchers);
  }

  @Override
  public boolean matches(HttpServletRequest request) {
    return andRequestMatcher.matches(request);
  }
}
