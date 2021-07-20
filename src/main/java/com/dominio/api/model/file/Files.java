package com.dominio.api.model.file;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "File")
public class Files {

    @Id
    private String uuid;
    private String name;
}
