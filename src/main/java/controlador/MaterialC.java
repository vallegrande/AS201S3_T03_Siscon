package controlador;

import dao.MaterialImpl;
import dto.MaterialDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import services.MaterialServiceImpl;

@Data
@Named(value = "materialC")
@SessionScoped
public class MaterialC implements Serializable {

    MaterialServiceImpl materialServiceImpl = new MaterialServiceImpl();
    private List<MaterialDTO> listMaterialDTO = new ArrayList<>();
    private MaterialDTO materialDTO = new MaterialDTO();
    private MaterialDTO seleccionado = new MaterialDTO();
    private List<MaterialDTO> listado = new ArrayList<>();
    private MaterialImpl material = new MaterialImpl();

    public MaterialC() {
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() {
        try {
            materialServiceImpl.crear(materialDTO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            materialServiceImpl.modificar(seleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Modificado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void modificarEstado(Integer IDMAT) {
        try {
            materialServiceImpl.modificarEstado(IDMAT);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Inactivar", "Inactivado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listMaterialDTO = new ArrayList<>();
            listMaterialDTO = materialServiceImpl.listar();
        } catch (Exception e) {

        }
    }

    public List getListardato() {
        try {
            listado = new ArrayList<>();
            listado = material.listar();
        } catch (Exception e) {
        }
        return listado;
    }

    public void limpiar() {
        materialDTO = new MaterialDTO();
    }

}
