package controlador;

import dao.Asignacion_MaterialImpl;
import dto.Asignacion_MaterialDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import services.Asignacion_MaterialServiceImpl;

@Data
@Named(value = "asignacion_MaterialC")
@SessionScoped
public class Asignacion_MaterialC implements Serializable {

    Asignacion_MaterialServiceImpl asignacion_MaterialServiceImpl = new Asignacion_MaterialServiceImpl();
    private List<Asignacion_MaterialDTO> listAsignacionDTO = new ArrayList<>();
    private Asignacion_MaterialDTO asignacionDTO = new Asignacion_MaterialDTO();
    private Asignacion_MaterialDTO seleccionado = new Asignacion_MaterialDTO();
    private List<Asignacion_MaterialDTO> listado = new ArrayList<>();
    private Asignacion_MaterialImpl asignacion = new Asignacion_MaterialImpl();

    public Asignacion_MaterialC() {
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
            asignacion_MaterialServiceImpl.crear(asignacionDTO);
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
            asignacion_MaterialServiceImpl.modificar(seleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Modificado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void modificarEstado(Integer IDASI) {
        try {
            asignacion_MaterialServiceImpl.modificarEstado(IDASI);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Inactivar", "Inactivado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listAsignacionDTO = new ArrayList<>();
            listAsignacionDTO = asignacion_MaterialServiceImpl.listar();
        } catch (Exception e) {

        }
    }

    public void limpiar() {
        asignacionDTO = new Asignacion_MaterialDTO();
    }

}
