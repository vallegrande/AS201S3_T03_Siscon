package dao;

import dto.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioImpl extends Conexion {

    public void crear(Usuario usu) throws Exception {
        String sql = "INSERT INTO USUARIO"
                + "(IDARE, NOMUSU, DNIUSU, SEXUSU, CELUSU, ESTUSU)"
                + " values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usu.getIDARE());
            ps.setString(2, usu.getNOMUSU());
            ps.setString(3, usu.getDNIUSU());
            ps.setString(4, usu.getSEXUSU());
            ps.setString(5, usu.getCELUSU());
            ps.setString(6, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en insert UsuDAO " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificar(Usuario usu) throws Exception {
        String sql = "UPDATE USUARIO SET"
                + " IDARE=?, NOMUSU=?, DNIUSU=?, SEXUSU=?, CELUSU=?, ESTUSU=? WHERE IDUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usu.getIDARE());
            ps.setString(2, usu.getNOMUSU());
            ps.setString(3, usu.getDNIUSU());
            ps.setString(4, usu.getSEXUSU());
            ps.setString(5, usu.getCELUSU());
            ps.setString(6, "A");
            ps.setInt(7, usu.getIDUSU());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en modificar UsuDAO" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificarEst(Usuario usu) throws Exception {
        String sql = "UPDATE USUARIO SET"
                + " ESTUSU = 'I' WHERE IDUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usu.getIDUSU());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Usuario" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public List<UsuarioDTO> listar() throws Exception {
        List<UsuarioDTO> listado = new ArrayList<>();
        UsuarioDTO pro;
        String sql = " SELECT USUARIO.IDUSU, USUARIO.NOMUSU, USUARIO.DNIUSU, USUARIO.SEXUSU, USUARIO.CELUSU, USUARIO.ESTUSU, AREA.IDARE, AREA.NOMARE from USUARIO\n"
                + " INNER JOIN AREA ON AREA.IDARE = USUARIO.IDARE WHERE USUARIO.ESTUSU = 'A' ORDER BY DNIUSU";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pro = new UsuarioDTO();
                pro.setIDUSU(rs.getInt("IDUSU"));
                pro.setDNIUSU(rs.getString("DNIUSU"));
                pro.setSEXUSU(rs.getString("SEXUSU"));
                pro.setCELUSU(rs.getString("CELUSU"));
                pro.setESTUSU(rs.getString("ESTUSU"));
                pro.setNOMUSU(rs.getString("NOMUSU"));
                pro.setIDARE(rs.getInt("IDARE"));
                pro.setArea(rs.getString("NOMARE"));
                listado.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lista Usuario" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }

    public List<UsuarioDTO> dato() throws Exception {
        List<UsuarioDTO> lista = new ArrayList<>();
        UsuarioDTO asi;
        String sql = "SELECT\n"
                + "U.IDUSU, U.NOMUSU\n"
                + "FROM USUARIO U\n"
                + "WHERE U.IDUSU NOT IN (SELECT IDUSU FROM asignacion_material WHERE ESTASI = 'A')";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asi = new UsuarioDTO();
                asi.setIDUSU(rs.getInt("IDUSU"));
                asi.setNOMUSU(rs.getString("NOMUSU"));
                lista.add(asi);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error en la lista Asignacion " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }
}
