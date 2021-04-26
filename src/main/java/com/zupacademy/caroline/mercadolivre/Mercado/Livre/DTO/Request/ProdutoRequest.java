package com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Categoria;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.ExistsId;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.UniqueValueValid;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

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
    @UniqueValueValid(classe = Produto.class, field = "nome")
    private String nome;
    @Positive
    private int quantidade;
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
    private List<CaracteristicaRequest> caracteristica = new ArrayList<>();

    @Deprecated
    public ProdutoRequest() {
    }

    public ProdutoRequest(String nome, int quantidade, String descricao, @NonNull BigDecimal valor,
                          Long idCategoria, List<CaracteristicaRequest> caracteristica) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristica.addAll(caracteristica);
    }



    public List<CaracteristicaRequest> getCaracteristica() {
        return caracteristica;
    }

    public Produto converter(EntityManager manager, Usuario usuario ) {
        Categoria categoria = manager.find(Categoria.class,idCategoria);
        return new Produto(nome,quantidade,descricao,valor,categoria, usuario, caracteristica );
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", idCategoria=" + idCategoria +
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


    public Set <String> temCaracteristicasIguais() {
          HashSet <String> nomeIguais = new HashSet<>();
        HashSet <String> resultados = new HashSet<>();

        for(CaracteristicaRequest request: caracteristica){
            String nome = request.getNome();
                if (!nomeIguais.add(nome)) {
                        resultados.add(nome);               }
            }
            return resultados;
            }

    }



