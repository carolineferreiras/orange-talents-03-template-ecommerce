package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Categoria;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid.ExistsId;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid.UniqueValueValid;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    @UniqueValueValid(classe = Produto.class,field = "nome")
    private String nome;
    @Positive
    @NotNull
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();


    @Deprecated
    public ProdutoRequest() {
    }

    public ProdutoRequest(String nome, Integer quantidade, String descricao, BigDecimal valor,
                          Long idCategoria, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(
            List<CaracteristicaRequest> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "NovoProdutoRequest [nome=" + nome + ", quantidade=" + quantidade
                + ", descricao=" + descricao + ", valor=" + valor
                + ", idCategoria=" + idCategoria + ", caracteristicas="
                + caracteristicas + "]";
    }


    public Produto toModel(EntityManager manager, Usuario dono) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, quantidade, descricao, valor, categoria, dono,
                caracteristicas);
    }

    public Set<String> temCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (CaracteristicaRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }




}


