package br.com.projeto.MongoDB.Projeto.Mongo.DB.Repositories;

import br.com.projeto.MongoDB.Projeto.Mongo.DB.Entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
    ClientEntity findByrazaoSocial(String razaoSocial);

    ClientEntity deleteByrazaoSocial(String razaoSocial);
}