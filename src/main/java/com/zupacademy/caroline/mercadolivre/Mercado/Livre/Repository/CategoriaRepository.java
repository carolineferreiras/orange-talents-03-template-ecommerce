package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Repository;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository <Categoria, Long> {
}
