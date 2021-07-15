package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Marca implements Serializable {

    private Integer IDMAR;
    private String NOMMAR;
    private String ESTMAR;
}
