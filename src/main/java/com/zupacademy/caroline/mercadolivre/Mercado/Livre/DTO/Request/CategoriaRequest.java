package com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Categoria;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.UniqueValueValid;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    @Positive
    private Long idCategoriaMae;
    @NotBlank
    @UniqueValueValid(field = "nome", classe = Categoria.class)
    private String nome;

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "idCategoriaMae=" + idCategoriaMae +
                ", nome='" + nome + '\'' +
                '}';
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria= new Categoria(nome);
        if(idCategoriaMae!= null){
            Categoria categoriaMae = manager.find(Categoria.class,idCategoriaMae);
            Assert.notNull(categoriaMae,"O id da categoria mae precisa ser valido");
            categoria.setMae(categoriaMae);
        }
            return categoria;
    }
}
