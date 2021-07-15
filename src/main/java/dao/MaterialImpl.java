package dao;

import dto.MaterialDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Material;

public class MaterialImpl extends Conexion {

    public void crear(Material mat) throws Exception {
        String sql = "Insert into Material"
                + "(IDMAR, NOMMAT, PREMAT, FECCOM, ESTMAT)"
                + " values(?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, mat.getIDMAR());
            ps.setString(2, mat.getNOMMAT());
            ps.setDouble(3, mat.getPREMAT());
            ps.setDate(4, new java.sql.Date(mat.getFECCOM().getTime()));
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en insert Material " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificar(Material mat) throws Exception {
        String sql = "update Material set "
                + " IDMAR=?, NOMMAT=?, PREMAT=?, FECCOM=?, ESTMAT=? where IDMAT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, mat.getIDMAR());
            ps.setString(2, mat.getNOMMAT());
            ps.setDouble(3, mat.getPREMAT());
            ps.setDate(4, new java.sql.Date(mat.getFECCOM().getTime()));
            ps.setString(5, "A");
            ps.setInt(6, mat.getIDMAT());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en modificar Material" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificarEst(Material mat) throws Exception {
        String sql = "update Material set"
                + " ESTMAT = 'I' where IDMAT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, mat.getIDMAT());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Material" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public List<MaterialDTO> listar() throws Exception {
        List<MaterialDTO> listado = new ArrayList<>();
        MaterialDTO mat;
        String sql = "SELECT MATERIAL.IDMAT, MATERIAL.NOMMAT, MATERIAL.PREMAT, MATERIAL.FECCOM, MATERIAL.ESTMAT, MARCA.IDMAR, MARCA.NOMMAR from MATERIAL\n"
                + "INNER JOIN MARCA ON MARCA.IDMAR = MATERIAL.IDMAR WHERE MATERIAL.ESTMAT = 'A' ORDER BY NOMMAT";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mat = new MaterialDTO();
                mat.setIDMAT(rs.getInt("IDMAT"));
                mat.setNOMMAT(rs.getString("NOMMAT"));
                mat.setPREMAT(rs.getDouble("PREMAT"));
                mat.setFECCOM(rs.getDate("FECCOM"));
                mat.setESTMAT(rs.getString("ESTMAT"));
                mat.setIDMAR(rs.getInt("IDMAR"));
                mat.setMarca(rs.getString("NOMMAR"));
                listado.add(mat);
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
