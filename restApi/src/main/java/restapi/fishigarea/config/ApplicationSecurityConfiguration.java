package restapi.fishigarea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import restapi.fishigarea.constants.AuthorityConstants;
import restapi.fishigarea.service.UserService;
import restapi.fishigarea.web.filters.JwtAuthenticationFilter;
import restapi.fishigarea.web.filters.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationSecurityConfiguration(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().disable()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/users/register","/users/login").anonymous()
//                .anyRequest().anonymous()
//                .and()
//                .formLogin().disable();


        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/fish/*","/api/fish/all/*","/api/fish/add","/api/fishpond/delete/*","/api/fishpond/all/*","/api/fishpond/*","/api/regions/*","/api/users/register",
                        "/api/users/signin", "/api/users/exists/*","/api/users/id/*").permitAll()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/api/exercises/create","/api/users/all").hasAuthority(AuthorityConstants.AUTHORITY_ROOT)
                .antMatchers( "/api/users/promote", "/api/users/demote").hasAuthority(AuthorityConstants.AUTHORITY_ADMIN)
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration()
                .applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addExposedHeader(
                "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, "
                        + "Content-Type, Access-Control-Request-Method, Custom-Filter-Header");

        source.registerCorsConfiguration("/**"
                , corsConfiguration);

        return source;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}