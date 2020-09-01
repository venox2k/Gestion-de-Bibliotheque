package Ensah.GDBC.Configuration ;

import Ensah.GDBC.auth.MyUserDetailsService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class  securityConfiguration  extends WebSecurityConfigurerAdapter  {



    @Autowired
    private final MyUserDetailsService userService ;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder()) ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/SignUp" ,"/Register","/chat", "/js/**", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()

                .and()
                .logout()
                .permitAll()

                .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login");


        //  loead  the resources
        // http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();


        //for load  the page of  login  ;
        //  http.formLogin().loginPage("/login").permitAll().and().logout()   ;
        // here we need to define our page of login and   logout
    }




    // crypte the  password
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }






}
