package com.manager.person.entity.repository;

import com.manager.person.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoa, UUID> {


}
