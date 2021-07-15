package services;

import dao.Asignacion_MaterialImpl;
import dto.Asignacion_MaterialDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Asignacion_Material;

public class Asignacion_MaterialServiceImpl implements Asignacion_MaterialService {

    Asignacion_MaterialImpl dao = new Asignacion_MaterialImpl();
    
    @Override
    public void crear(Asignacion_MaterialDTO asignacion_MaterialDTO) {
        Asignacion_Material asi = new Asignacion_Material();
        asi.setIDASI(asignacion_MaterialDTO.getIDASI());
        asi.setFECASI(asignacion_MaterialDTO.getFECASI());
        asi.setESTASI(asignacion_MaterialDTO.getESTASI());
        asi.setIDUSU(asignacion_MaterialDTO.getIDUSU());
        asi.setIDMAT(asignacion_MaterialDTO.getIDMAT());
        try {
            dao.crear(asi);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificar(Asignacion_MaterialDTO asignacion_MaterialDTO) {
        Asignacion_Material asi = new Asignacion_Material();
        asi.setIDASI(asignacion_MaterialDTO.getIDASI());
        asi.setFECASI(asignacion_MaterialDTO.getFECASI());
        asi.setESTASI(asignacion_MaterialDTO.getESTASI());
        asi.setIDUSU(asignacion_MaterialDTO.getIDUSU());
        asi.setIDMAT(asignacion_MaterialDTO.getIDMAT());
        try {
            dao.modificar(asi);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEstado(Integer IDASI) {
        Asignacion_Material asi = new Asignacion_Material();
        asi.setIDASI(IDASI);
        asi.setESTASI("I");
        try {
            dao.modificarEst(asi);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Asignacion_MaterialDTO> listar() throws Exception {
        return dao.listar();
    }

}
