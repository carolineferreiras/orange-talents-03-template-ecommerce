package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Response;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.DetalheCaracteristicaProduto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.PerguntaRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

public class DetalheProdutoView {



    private  String descricao;
    private  String nome;
    private  BigDecimal preco;
    private  Set<DetalheCaracteristicaProduto> caracteristicas;
    private  Set<String> linkImagens;
    private  SortedSet<String> perguntas;
    private  Set<Map<String, String>> opinioes;
    private  double mediaNotas;
    
    public DetalheProdutoView(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.mapeiaCaracteristicas(DetalheCaracteristicaProduto::new);
        this.linkImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
        this.opinioes = produto.mapeiaOpinioes (opiniao -> {
            return Map.of("titulo",opiniao.getTitulo(),"descricao", opiniao.getDescricao());
        });

        Set<Integer> notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
        IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
        OptionalDouble  average= mapToInt.average();
        if (average.isPresent()){
            this.mediaNotas = average.getAsDouble();
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheCaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinkImagens() {
        return linkImagens;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }
}
