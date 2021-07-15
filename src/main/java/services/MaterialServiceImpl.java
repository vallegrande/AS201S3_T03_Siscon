package services;

import dao.MaterialImpl;
import dto.MaterialDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Material;

public class MaterialServiceImpl implements MaterialService {

    MaterialImpl dao = new MaterialImpl();

    @Override
    public void crear(MaterialDTO materialDTO) {
        Material mat = new Material();
        mat.setIDMAT(materialDTO.getIDMAT());
        mat.setNOMMAT(materialDTO.getNOMMAT());
        mat.setPREMAT(materialDTO.getPREMAT());
        mat.setFECCOM(materialDTO.getFECCOM());
        mat.setESTMAT(materialDTO.getESTMAT());
        mat.setIDMAR(materialDTO.getIDMAR());
        try {
            dao.crear(mat);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar(MaterialDTO materialDTO) {
        Material mat = new Material();
        mat.setIDMAT(materialDTO.getIDMAT());
        mat.setNOMMAT(materialDTO.getNOMMAT());
        mat.setPREMAT(materialDTO.getPREMAT());
        mat.setFECCOM(materialDTO.getFECCOM());
        mat.setESTMAT(materialDTO.getESTMAT());
        mat.setIDMAR(materialDTO.getIDMAR());
        try {
            dao.modificar(mat);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEstado(Integer IDMAT) {
        Material mat = new Material();
        mat.setIDMAT(IDMAT);
        mat.setESTMAT("I");
        try {
            dao.modificarEst(mat);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<MaterialDTO> listar() throws Exception {
        return dao.listar();
    }

}
