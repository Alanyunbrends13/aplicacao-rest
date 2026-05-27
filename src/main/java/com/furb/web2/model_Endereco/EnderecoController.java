package com.furb.web2.model_Endereco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//deletar endereço por id

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoRepository getEnderecoRepository() {
        return enderecoRepository;
    }

    @GetMapping
    public List<Endereco> listaEndereco(){
        return enderecoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> adicionaEndereco(@RequestBody Endereco_Request request){
        if(request.bairro() == null || request.cidade() == null || request.logradouro() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há dados faltando na requisição!");
        }

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.logradouro());
        endereco.setBairro(request.bairro());
        endereco.setCidade(request.cidade());

        if(request.bairro().isEmpty() || request.cidade().isEmpty() || request.logradouro().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há dados faltando na requisição!");
        }

        return ResponseEntity.ok(enderecoRepository.save(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEndereco(@PathVariable Long id){
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if(enderecoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Endereço não encontrado!");
        }

        Endereco endereco = enderecoOptional.get();

        if(!endereco.getUsuarios().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há usuários cadastrados neste endereço!");
        }

        enderecoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Endereço Deletado com Sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaEnderecoEspecifico(@PathVariable Long id){
        if(enderecoRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Endereço não encontrado!");
        }

        return ResponseEntity.ok(enderecoRepository.findById(id));
    } 

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaEndereco(@RequestBody Endereco_Request request, @PathVariable Long id){
        
        Optional<Endereco> usuarioOptional = enderecoRepository.findById(id);

        if(usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Endereço não encontrado!");
        }else if(request.bairro().isEmpty() || request.cidade().isEmpty() || request.logradouro().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há dados faltando na requisição!");
        }

        Endereco endereco = usuarioOptional.get();

        endereco.setBairro(request.bairro());
        endereco.setCidade(request.cidade());
        endereco.setLogradouro(request.logradouro());
        
        return ResponseEntity.ok(enderecoRepository.save(endereco));
    }
}
