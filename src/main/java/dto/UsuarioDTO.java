package dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

    private Integer IDUSU;
    private Integer IDARE;
    private String Area;
    private String NOMUSU;
    private String DNIUSU;
    private String CELUSU;
    private String SEXUSU;
    private String ESTUSU;
}
