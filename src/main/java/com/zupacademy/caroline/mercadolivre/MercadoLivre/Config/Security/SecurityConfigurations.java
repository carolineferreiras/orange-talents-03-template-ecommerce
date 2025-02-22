package com.zupacademy.caroline.mercadolivre.MercadoLivre.Config.Security;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configuracoes de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/").permitAll()
//                .antMatchers(HttpMethod.POST,"/auth").permitAll()
//                .antMatchers(HttpMethod.POST,"/usuario").permitAll()
//                .antMatchers(HttpMethod.POST,"/produtos").permitAll()
//                .antMatchers(HttpMethod.POST,"/categorias").permitAll()
//                .antMatchers(HttpMethod.POST,"/produtos/1/imagens").permitAll()
                //opiniao
//                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    //Configuracoes de recursos estaticos(js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }



}