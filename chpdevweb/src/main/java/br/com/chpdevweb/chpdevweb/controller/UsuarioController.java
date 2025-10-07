package br.com.chpdevweb.chpdevweb.controller;

import br.com.chpdevweb.chpdevweb.model.Usuario;
import br.com.chpdevweb.chpdevweb.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository repo;
    public UsuarioController(UsuarioRepository repo) { this.repo = repo; }

    // CRIAR: /usuarios?nome=Fulano
    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestParam String nome) {
        return ResponseEntity.ok(repo.save(new Usuario(nome)));
    }

    @GetMapping
    public ResponseEntity<?> listar() { return ResponseEntity.ok(repo.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<?> obter(@PathVariable Long id) {
        return repo.findById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ATUALIZAR NOME: /usuarios/{id}?nome=NovoNome
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestParam String nome) {
        var u = repo.findById(id).orElse(null);
        if (u == null) return ResponseEntity.notFound().build();
        u.setNome(nome);
        return ResponseEntity.ok(repo.save(u));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}