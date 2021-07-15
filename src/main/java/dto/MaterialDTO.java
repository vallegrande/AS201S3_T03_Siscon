package dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class MaterialDTO implements Serializable {

    private Integer IDMAT;
    private Integer IDMAR;
    private String Marca;
    private String NOMMAT;
    private Double PREMAT;
    private Date FECCOM;
    private String ESTMAT;
}
