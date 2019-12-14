package com.VictorianApp.security.config;

import com.VictorianApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {

        class UsersRowMapper implements RowMapper<User> {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User User = new User();
                User.setLogin(resultSet.getString("login"));
                User.setHaslo(resultSet.getString("haslo"));
                User.setRola(resultSet.getString("rola"));
                return User;
            }
        }

        final String sqlSelectUsers = "SELECT login, haslo, rola FROM konto " +
                "JOIN rola USING (rola)";
        List<User> Users = new ArrayList<>
                (jdbcTemplate.query(sqlSelectUsers, new UsersRowMapper()));

        for( User user : Users ) {
            String login = user.getLogin();
            String password = user.getHaslo();
            String role = user.getRola().toUpperCase();

            auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                    .withUser(login)
                    .password(passwordEncoder.encode(password))
                    .roles(role);
        }

    }

    @Bean
    public AuthenticationSuccessHandler myAuthSuccessHandler(){
        return new MyUrlAuthSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                 .cors().and()
                 .csrf().disable()
                 .authorizeRequests()
                 .antMatchers( "/api/**")
                 .permitAll().anyRequest().authenticated().and().httpBasic();
    }

}