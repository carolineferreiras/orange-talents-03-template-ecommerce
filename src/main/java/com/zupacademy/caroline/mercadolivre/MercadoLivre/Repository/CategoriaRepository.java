package com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository <Categoria, Long> {
}
