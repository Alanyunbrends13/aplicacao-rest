package com.furb.web2.model_Equipamento;

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


@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    /**
     * Método Get que retorna todos os equipamentos cadastrados
     * @return - tipo List de todos os equipamentos da tabela do banco Equipamento
     */
    @GetMapping
    public List<Equipamento> listaEquipamento(){
        return equipamentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> adicionarEquipamento(@RequestBody EquipamentoRequest request){
        Equipamento equipamento = new Equipamento();

        if(request.descricao() == null || request.dtRecebimento() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há dados faltando na requisição!");
        }

        equipamento.setDescricao(request.descricao());
        equipamento.setDtRecebimento(request.dtRecebimento());

        return ResponseEntity.ok(equipamentoRepository.save(equipamento));
    }

    @DeleteMapping("/{id}")
    public void removeEquipamento(@PathVariable Long id){
        equipamentoRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaEquipamentoEspecifico(@PathVariable Long id){
        if(equipamentoRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Equipamento não encontrado!");
        }

        return ResponseEntity.ok(equipamentoRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaEquipamento(@PathVariable Long id, @RequestBody EquipamentoRequest request){
        Optional<Equipamento> equipamentoOptional = equipamentoRepository.findById(id);

        if(equipamentoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Equipamento não encontrado!");
        }else if(request.descricao() == null || request.dtRecebimento() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO: Há dados faltando na requisição!");
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setDescricao(request.descricao());
        equipamento.setDtRecebimento(request.dtRecebimento());

        return ResponseEntity.ok(equipamentoRepository.save(equipamento));
    }



}
