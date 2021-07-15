package model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Material implements Serializable {

    private Integer IDMAT;
    private Integer IDMAR;
    private String NOMMAT;
    private Double PREMAT;
    private Date FECCOM;
    private String ESTMAT;
    
}
