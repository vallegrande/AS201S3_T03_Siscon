package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Area;

public class AreaImpl extends Conexion implements ICRUD<Area> {

    @Override
    public void registrar(Area area) throws Exception {
        String sql = "insert into Area"
                + "(NOMARE, ESTARE) values (?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, area.getNOMARE());
            ps.setString(2, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en registrar Area" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Area area) throws Exception {
        String sql = "UPDATE AREA SET"
                + " NOMARE=?, ESTARE=? WHERE IDARE=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, area.getNOMARE());
            ps.setString(2, "A");
            ps.setInt(3, area.getIDARE());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Area" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificarEst(Area area) throws Exception {
        String sql = "UPDATE AREA SET"
                + " ESTARE = 'I' WHERE IDARE=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, area.getIDARE());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Estado de Area" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void eliminar(Area gen) throws Exception {

    }

    @Override
    public List<Area> listar() throws Exception {
        List<Area> listado = new ArrayList<>();
        Area are;
        String sql = "SELECT * FROM AREA WHERE AREA.ESTARE = 'A' ORDER BY NOMARE";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                are = new Area();
                are.setIDARE(rs.getInt("IDARE"));
                are.setNOMARE(rs.getString("NOMARE"));
                are.setESTARE(rs.getString("ESTARE"));
                listado.add(are);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lista area" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
}
