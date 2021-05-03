package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.NotaFiscalRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.RankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ComunicacoesController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping(value = "/notas-fiscais")
    public String criaNota(@Valid @RequestBody NotaFiscalRequest request) throws InterruptedException {

        //Constroi o email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Segue sua nota fiscal em anexo");
        message.setTo("et74745@gmail.com");
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

    @PostMapping(value = "/ranking")
    public String ranking(@Valid @RequestBody RankingRequest request) throws InterruptedException {


        //Constroi o email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Segue sua nota fiscal em anexo");
        message.setTo("et74745@gmail.com");
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
