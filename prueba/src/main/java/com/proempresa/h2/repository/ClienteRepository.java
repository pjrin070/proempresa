package com.proempresa.h2.repository;

import com.proempresa.h2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  //List<Tutorial> findByPublished(boolean published);

  //List<Tutorial> findByTitleContainingIgnoreCase(String title);
}
