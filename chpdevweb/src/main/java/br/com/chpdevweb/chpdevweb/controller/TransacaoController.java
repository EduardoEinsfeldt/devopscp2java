package br.com.chpdevweb.chpdevweb.controller;

import br.com.chpdevweb.chpdevweb.model.Transacao;
import br.com.chpdevweb.chpdevweb.model.Usuario;
import br.com.chpdevweb.chpdevweb.repository.TransacaoRepository;
import br.com.chpdevweb.chpdevweb.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final TransacaoRepository txRepo;
    private final UsuarioRepository userRepo;

    public TransacaoController(TransacaoRepository txRepo, UsuarioRepository userRepo) {
        this.txRepo = txRepo; this.userRepo = userRepo;
    }

    // CREATE: /transacoes?usuarioId=1&valor=10.5
    @PostMapping
    public ResponseEntity<?> criar(@RequestParam long usuarioId, @RequestParam double valor) {
        var u = userRepo.findById(usuarioId).orElse(null);
        if (u == null) return ResponseEntity.badRequest().body("usuarioId inválido");
        var t = new Transacao();
        t.setUsuario(u);
        t.setValor(valor);
        return ResponseEntity.ok(txRepo.save(t));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> listar() { return ResponseEntity.ok(txRepo.findAll()); }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> obter(@PathVariable Long id) {
        return txRepo.findById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE (só o valor): /transacoes/{id}?valor=99.9
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarValor(@PathVariable Long id, @RequestParam double valor) {
        var t = txRepo.findById(id).orElse(null);
        if (t == null) return ResponseEntity.notFound().build();
        t.setValor(valor);
        return ResponseEntity.ok(txRepo.save(t));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (!txRepo.existsById(id)) return ResponseEntity.notFound().build();
        txRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
