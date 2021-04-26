package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.CaracteristicaRequest;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nome;
    @Positive
    private  int quantidade;
    @NotBlank @Length (max =  1000)
    private String descricao;
    @NotNull @Positive
    private BigDecimal valor;
    @ManyToOne
    @NotNull @Valid
    private  Categoria categoria;
    @ManyToOne
    @NotNull @Valid
    private Usuario usuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristica = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, int quantidade, String descricao, BigDecimal valor, Categoria categoria, Usuario usuario, Collection<CaracteristicaRequest> caracteristica) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristica.addAll(caracteristica.stream().map(request -> request.toModel(this )).collect(Collectors.toSet()));

        Assert.isTrue(this.caracteristica.size()>=3,"Todo produto precisa ter no minimo 3 caracteristicas ");
    }



    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", caracteristica=" + caracteristica +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
