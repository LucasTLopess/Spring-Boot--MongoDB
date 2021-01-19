package br.com.projeto.MongoDB.Projeto.Mongo.DB.Entities;

import com.mongodb.lang.Nullable;
import jdk.jfr.Unsigned;
import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection="cliente")
public class ClientEntity {

    @Id
    @Generated
    private String id;

    private String razaoSocial;

    public ClientEntity(String id, String razaoSocial) {
        this.id = id;
        this.razaoSocial = razaoSocial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
