package in.sdqali.spring.csrf.config;

import in.sdqali.spring.csrf.auth.filter.CsrfGrantingFilter;
import in.sdqali.spring.csrf.auth.matcher.NoAntPathRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

@EnableWebSecurity
@Configuration
@EnableSpringHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    String[] patterns = new String[] {
        "/login",
        "/bower_components/**/*",
        "/app/**/*",
        "/index.html",
        "/home.html",
        "/signin.html",
        "/favicon.ico"
    };

    http
        .authorizeRequests()
        .antMatchers(patterns)
        .permitAll()
        .antMatchers("/hello/**")
        .hasRole("USER")
        .and()
        .csrf()
        .csrfTokenRepository(csrfTokenRepository())
        .requireCsrfProtectionMatcher(csrfProtectionMatcher(patterns))
        .and()
        .httpBasic()
        .and()
        .addFilterAfter(new CsrfGrantingFilter(), SessionManagementFilter.class);
  }

  private NoAntPathRequestMatcher csrfProtectionMatcher(String[] patterns) {
    return new NoAntPathRequestMatcher(patterns);
  }

  private CsrfTokenRepository csrfTokenRepository() {
    return new HttpSessionCsrfTokenRepository();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
  }

  @Bean
  public SessionRepository sessionRepository() {
    return new MapSessionRepository();
  }

  @Bean
  public HeaderHttpSessionStrategy sessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }
}
