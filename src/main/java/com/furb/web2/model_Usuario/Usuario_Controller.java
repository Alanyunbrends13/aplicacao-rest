package com.furb.web2.model_Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/usuarios")
public class Usuario_Controller {
    @Autowired
    private usuarioRepository usuarioRepository;

    // GET - Retorna todos os usuários
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // POST - Adiciona usuário no banco
    @PostMapping
    public ResponseEntity<?> adicionarUsuario(@RequestBody UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setRua(request.rua());
        usuario.setIdade(request.idade());

        if(request.idade() == null || request.nome() == null || request.rua() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há dados faltando na requisição!");
        }

        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    //DELETE - Remove usuários
    //id seria o identificador do usuário
    @DeleteMapping("/{id}")
    public void removeUsuario(@PathVariable Long id){

        usuarioRepository.deleteById(id);
    }

    //GET - busca usuários pelo id
    @GetMapping("/{id}")
    public ResponseEntity<?> listaUsuarioEspecifico(@PathVariable Long id){
        if(usuarioRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Usuário não encontrado!");
        }


        return ResponseEntity.ok(usuarioRepository.findById(id));
    }

    //PUT - altera usuários de acordo com o id no banco
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaUsuario(@RequestBody UsuarioRequest request, @PathVariable Long id){
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Usuário não encontrado!");
        }else if(request.idade() == null || request.nome() == null || request.rua() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há dados faltando na requisição!");
        }

        Usuario usuario = usuarioOptional.get();

        usuario.setNome(request.nome());
        usuario.setIdade(request.idade());
        usuario.setRua(request.rua());
        
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }
    


}
