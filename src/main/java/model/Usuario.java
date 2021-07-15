package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Usuario implements Serializable {

    private Integer IDUSU;
    private Integer IDARE;
    private String NOMUSU;
    private String DNIUSU;
    private String CELUSU;
    private String SEXUSU;
    private String ESTUSU;

}
