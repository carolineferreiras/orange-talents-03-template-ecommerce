package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;
    @PastOrPresent @NotNull
    private LocalDateTime criadoEm = LocalDateTime.now();

    public Usuario() {
    }

    public Usuario(
            String email,
            @Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email),"Email não pode ser em brancio");
        Assert.notNull(senhaLimpa,"O objeto do tipi senha limpa não poder nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();
    }


    public Usuario(String email, String senha) {
    }


}
