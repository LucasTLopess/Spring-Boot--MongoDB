package br.com.projeto.MongoDB.Projeto.Mongo.DB.Controllers;


import br.com.projeto.MongoDB.Projeto.Mongo.DB.Entities.ClientEntity;
import br.com.projeto.MongoDB.Projeto.Mongo.DB.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientRepository repository;

    @GetMapping
    public ResponseEntity<?> list(){
        List<ClientEntity> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path="/{cnpj}")
    public ResponseEntity<ClientEntity> FindByRazaoSocial(@PathVariable String cnpj){

        ClientEntity clientEntity = repository.findByrazaoSocial(cnpj);

        if(Objects.isNull(clientEntity)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(clientEntity);
        }

    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody ClientEntity client){
       if (Objects.isNull(repository.findByrazaoSocial(client.getRazaoSocial()))){
           return ResponseEntity.ok(repository.save(client));
       }else {
           return ResponseEntity.badRequest().body(client);
       }
    }

    @DeleteMapping("/delete/{cnpj}")
    public ResponseEntity<ClientEntity> deleteClient(@PathVariable String cnpj) {
        if (Objects.isNull((repository.findByrazaoSocial(cnpj)))) {
            return ResponseEntity.noContent().build();
        } else {
            repository.deleteByrazaoSocial(cnpj);
            return ResponseEntity.accepted().build();
        }
    }
}
