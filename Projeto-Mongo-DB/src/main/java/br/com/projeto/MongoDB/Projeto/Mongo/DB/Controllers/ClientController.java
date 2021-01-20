package br.com.projeto.MongoDB.Projeto.Mongo.DB.Controllers;


import br.com.projeto.MongoDB.Projeto.Mongo.DB.Entities.ClientEntity;
import br.com.projeto.MongoDB.Projeto.Mongo.DB.Repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping
    public ResponseEntity<?> list(){
        logger.info("Acesando serviço para listar todos os clients");
        List<ClientEntity> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path="/{cnpj}")
    public ResponseEntity<ClientEntity> FindByRazaoSocial(@PathVariable String cnpj){

        logger.info("Acessando Clint CNPJ: "+ cnpj);
        ClientEntity clientEntity = repository.findByrazaoSocial(cnpj);

        if(Objects.isNull(clientEntity)){
            logger.info("CNPJ:"+ cnpj +" não está cadastrado na nossa base de dados: ");
            return ResponseEntity.noContent().build();
        }
        else{
            logger.info("Apresenta Client CNPJ: " + cnpj );
            return ResponseEntity.ok(clientEntity);
        }

    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody ClientEntity client){
       if (Objects.isNull(repository.findByrazaoSocial(client.getRazaoSocial()))){
           logger.error("   ");
           return ResponseEntity.ok(repository.save(client));
       }else {
           logger.error("Não foi possivel salvar Client CNPJ: " + client.getRazaoSocial());
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
