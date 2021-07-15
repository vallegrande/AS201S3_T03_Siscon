package dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Asignacion_MaterialDTO implements Serializable {

    private Integer IDASI;
    private Integer IDUSU;
    private String Usuario;
    private Date FECASI;
    private Integer IDMAT;
    private String Material;
    private String ESTASI;

}
