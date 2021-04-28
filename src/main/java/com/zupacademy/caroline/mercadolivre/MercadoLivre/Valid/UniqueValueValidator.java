package com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValueValid, Object> {


    private String atributo;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UniqueValueValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void initialize(UniqueValueValid constraintAnnotation) {
        this.atributo=constraintAnnotation.field();
        this.domainClass=constraintAnnotation.classe();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        String jpql= "select 1 from "+domainClass.getSimpleName()
                +" where "+atributo
                +" =:obj";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("obj",obj);
        List<?> list= query.getResultList();

        Assert.isTrue(list.size()<=1,"Foi encontrado mais de um"+domainClass+"com o atributo" +domainClass+"="+obj);

        return list.isEmpty();
    }
}
