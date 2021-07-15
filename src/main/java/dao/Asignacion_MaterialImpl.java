package dao;

import dto.Asignacion_MaterialDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Asignacion_Material;

public class Asignacion_MaterialImpl extends Conexion {

    public void crear(Asignacion_Material asig) throws Exception {
        String sql = "Insert into ASIGNACION_MATERIAL"
                + "(IDUSU, FECASI, IDMAT, ESTASI)"
                + " values(?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, asig.getIDUSU());
            ps.setDate(2, new java.sql.Date(asig.getFECASI().getTime()));
            ps.setInt(3, asig.getIDMAT());
            ps.setString(4, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en insert Asignacion " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificar(Asignacion_Material asig) throws Exception {
        String sql = "update Asignacion_Material set "
                + " IDUSU=?, FECASI=?, IDMAT=?, ESTASI=? where IDASI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, asig.getIDUSU());
            ps.setDate(2, new java.sql.Date(asig.getFECASI().getTime()));
            ps.setInt(3, asig.getIDMAT());
            ps.setString(4, "A");
            ps.setInt(5, asig.getIDASI());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en modificar Asignacion" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificarEst(Asignacion_Material asig) throws Exception {
        String sql = "update Asignacion_Material set"
                + " ESTASI = 'I' where IDASI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, asig.getIDASI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Asignacion" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public List<Asignacion_MaterialDTO> listar() throws Exception {
        List<Asignacion_MaterialDTO> listado = new ArrayList<>();
        Asignacion_MaterialDTO asig;
        String sql = "SELECT ASIGNACION_MATERIAL.IDASI, ASIGNACION_MATERIAL.FECASI, ASIGNACION_MATERIAL.ESTASI, USUARIO.IDUSU, USUARIO.NOMUSU, MATERIAL.IDMAT, MATERIAL.NOMMAT from ASIGNACION_MATERIAL\n"
                + "INNER JOIN USUARIO ON USUARIO.IDUSU = ASIGNACION_MATERIAL.IDUSU \n"
                + "INNER JOIN MATERIAL ON MATERIAL.IDMAT = ASIGNACION_MATERIAL.IDMAT WHERE ASIGNACION_MATERIAL.ESTASI = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asig = new Asignacion_MaterialDTO();
                asig.setIDASI(rs.getInt("IDASI"));
                asig.setFECASI(rs.getDate("FECASI"));
                asig.setESTASI(rs.getString("ESTASI"));
                asig.setIDUSU(rs.getInt("IDUSU"));
                asig.setUsuario(rs.getString("NOMUSU"));
                asig.setIDMAT(rs.getInt("IDMAT"));
                asig.setMaterial(rs.getString("NOMMAT"));
                listado.add(asig);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lista Material" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }

}
