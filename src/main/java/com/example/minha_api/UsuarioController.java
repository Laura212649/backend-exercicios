package com.example.minha_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final List<String> usuarios = new ArrayList<>(List.of("Ana", "Bruno", "Carla"));


    @GetMapping
    public List<String> listar() {
        return usuarios;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> buscar(@PathVariable int id) {
        if (id < 0 || id >= usuarios.size()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios.get(id));

    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody String nome) {
        usuarios.add(nome);
        return ResponseEntity.status(HttpStatus.CREATED).body(nome);
    }
    @PutMapping
    public ResponseEntity<String> atualizar(@PathVariable int id, @RequestBody String novoNome) {
        if (id < 0 || id >= usuarios.size()) {
            return ResponseEntity.notFound().build();
        }
        usuarios.set(id, novoNome);
        return ResponseEntity.ok(novoNome);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (id < 0 || id >= usuarios.size()) {
            return ResponseEntity.notFound().build();
        }
        usuarios.remove(id);
        return ResponseEntity.noContent().build();
    }
}
