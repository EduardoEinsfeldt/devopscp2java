package br.com.chpdevweb.chpdevweb.repository;

import br.com.chpdevweb.chpdevweb.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {}
