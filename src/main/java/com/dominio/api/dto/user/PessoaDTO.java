package com.dominio.api.dto.user;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String uuid;
    private String name;
    private Integer age;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
