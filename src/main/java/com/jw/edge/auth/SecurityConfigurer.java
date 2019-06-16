package com.jw.edge.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    //忽视拦截静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/layui/**");
    }

    //配置spring security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().anyRequest().authenticated()
                //允许frame
                .and().headers().frameOptions().sameOrigin()
                //登录
                .and().formLogin().loginPage("/pages/login").defaultSuccessUrl("/").permitAll()
                //登出
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
//                deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler());
        //启用remember me功能
//        .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
//                .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
//        return new LogoutSuccessHandler() {
//            @Override
//            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                try {
//                    SecurityUser user = (SecurityUser) authentication.getPrincipal();
//                    logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
//                } catch (Exception e) {
//                    logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
//                }
//                httpServletResponse.sendRedirect("/login");
//            }
//        };
//    }


}
