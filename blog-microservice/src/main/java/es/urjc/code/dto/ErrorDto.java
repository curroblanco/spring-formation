package es.urjc.code.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ErrorDto {

    private int code;
    private String description;
    private String name;
}
