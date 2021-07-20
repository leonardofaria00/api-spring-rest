package com.dominio.api.model.nasa;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Nasa")
public class Nasa {
    @Id
    private String uuid;
    private String title;
    private String url;
    private String hdurl;
    private String media_type;
    private String copyright;
    private String date;
    private String explanation;
    private String service_version;
}
