package br.com.chpdevweb.chpdevweb.repository;

import br.com.chpdevweb.chpdevweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
