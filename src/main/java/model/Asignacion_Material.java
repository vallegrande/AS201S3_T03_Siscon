package model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Asignacion_Material implements Serializable {

    private Integer IDASI;
    private Integer IDUSU;
    private Date FECASI;
    private Integer IDMAT;
    private String ESTASI;

}
