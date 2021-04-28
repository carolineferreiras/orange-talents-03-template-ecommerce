package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.PerguntaRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Opiniao;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Pergunta;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


//@AuthenticationPrincipal
@RestController
public class PerguntaController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager manager;


    @PostMapping(value = "/produtos/{id}/perguntas")
    @Transactional
    public String cria(@RequestBody @Valid PerguntaRequest request , @PathVariable("id")Long id) {

        Produto produto = manager.find(Produto.class, id);
        //simulando o usuario logado
        Usuario comsumidor = usuarioRepository.findByEmail("email@email.com").get();
        Pergunta pergunta = request.toModel(produto,comsumidor);

        manager.persist(pergunta);

        //Constroi o email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Sua pergunta foi cadastrada com sucesso aguarde a resposta!");
        message.setTo(comsumidor.getEmail());
        message.setFrom("et74745@gmail.com");


        //Envia o email
        try {
            mailSender.send(message);
            return "Sua pergunta foi cadastrada com sucesso aguarde a resposta!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }

    }
}
