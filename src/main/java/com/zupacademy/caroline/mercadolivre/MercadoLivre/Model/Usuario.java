package com.zupacademy.caroline.mercadolivre.MercadoLivre.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Usuario  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate criadoEm;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Deprecated
    public Usuario() {
    }

    public Usuario(String email, String senha) {
    }

    public Usuario(@NotBlank @Email String email, @NotNull @Valid SenhaLimpa senhaLimpa) {
        this.email = email;
        this.senha = senhaLimpa.hash();
        this.criadoEm = LocalDate.now();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Object getId() {
        return id;
    }


}
